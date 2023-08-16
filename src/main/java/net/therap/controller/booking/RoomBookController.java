package net.therap.controller.booking;

import net.therap.dto.ButtonDto;
import net.therap.dto.ErrorMessageDto;
import net.therap.dto.SuccessMessageDto;
import net.therap.model.Booking;
import net.therap.model.Room;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.CustomerService;
import net.therap.service.RoomService;
import net.therap.util.Util;
import net.therap.validator.BookingValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private BookingValidator bookingValidator;

    @InitBinder("booking")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(bookingValidator);
    }

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

        return "booking/roomBookPage";
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

            return "booking/roomBookPage";
        }

        Room room = roomService.findById(roomId);

        if (!roomService.availableRoom(room, booking.getCheckInDate(), booking.getCheckOutDate())) {

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("Find available room for this date",
                    "/search?hotelName=&location=&priceMin=&priceMax=&numberOfBed=&checkIn="
                            + booking.getCheckInDate().toString() +
                            "&checkOut=" + booking.getCheckOutDate()));

            ErrorMessageDto errorMessageDto = new ErrorMessageDto("Can not book. Room is not available for this date",
                    buttonDtoList);

            model.addAttribute("errorMessageDto", errorMessageDto);

            return "errorMessage";
        }

        booking.setRoomNumber(roomService.getRoomNumber(room, booking.getCheckInDate(), booking.getCheckOutDate()));
        booking.setRoom(room);

        return "booking/guestImageUpload";
    }

    @PostMapping("/book/guestImageUpload")
    public String uploadProfilePicture(@RequestParam("guestImage") CommonsMultipartFile guestImage,
                                       @SessionAttribute(name = "booking") Booking booking, Model model,
                                       @PathVariable String roomId) {


        if (guestImage.isEmpty()) {
            model.addAttribute("error", "Please select an Image.");

            return "booking/guestImageUpload";
        }

        String extension = FilenameUtils.getExtension(guestImage.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            return "booking/guestImageUpload";
        }

        try {
            byte[] guestImageData = IOUtils.toByteArray(guestImage.getInputStream());
            booking.setGuestImage(guestImageData);
            bookingService.save(booking);

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Booking Details", "/booking/" + booking.getId()));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            SuccessMessageDto successMessageDto = new SuccessMessageDto("Successfully Booked", buttonDtoList);

            model.addAttribute("successMessageDto", successMessageDto);

            return "successMessage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Image.");

            return "booking/guestImageUpload";
        }
    }
}