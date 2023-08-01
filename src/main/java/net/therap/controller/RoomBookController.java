package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Room;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.CustomerService;
import net.therap.service.RoomService;
import net.therap.util.Util;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Controller
@RequestMapping("/room/{roomId}")
@SessionAttributes("booking")
public class RoomBookController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/book")
    public String showRoomBookPage(@PathVariable Long roomId, Model model, HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        if (sessionContext == null) {
            return "redirect:/customer/login";
        }

        Booking booking = Util.createBookingFromSessionContext(sessionContext, customerService);
        Room room = roomService.findById(roomId);
        model.addAttribute("booking", booking);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomPrice", room.getPrice());

        return "roomBookPage";
    }

    @PostMapping("/book")
    public String processRoomBook(@PathVariable Long roomId, @ModelAttribute("booking") @Valid Booking booking,
                                  BindingResult bindingResult, Model model, HttpSession httpSession) {


        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (sessionContext == null) {
            return "redirect:/customer/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roomId", roomId);

            return "roomBookPage";
        }

        Room room = roomService.findById(roomId);

        if (!roomService.availableRoom(room, booking.getCheckInDate(), booking.getCheckOutDate())) {

            return "redirect:/search?hotelName=&location=&priceMin=&priceMax=&numberOfBed=&checkIn="
                    + booking.getCheckInDate().toString() + "&checkOut=" + booking.getCheckOutDate();
        }

        booking.setRoomNumber(roomService.getRoomNumber(room, booking.getCheckInDate(), booking.getCheckOutDate()));
        booking.setRoom(room);

        return "guestImageUpload";
    }

    @PostMapping("/book/guestimageupload")
    public String uploadProfilePicture(@RequestParam("guestImage") CommonsMultipartFile guestImage,
                                       @SessionAttribute(name = "booking") Booking booking, Model model,
                                       @PathVariable String roomId) {


        if (guestImage.isEmpty()) {
            model.addAttribute("error", "Please select an Image.");

            return "guestImageUpload";
        }

        String extension = FilenameUtils.getExtension(guestImage.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            return "guestImageUpload";
        }

        try {
            byte[] guestImageData = IOUtils.toByteArray(guestImage.getInputStream());
            booking.setGuestImage(guestImageData);
            bookingService.save(booking);

            return "bookingConfirmationPage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Image.");

            return "guestImageUpload";
        }
    }
}
