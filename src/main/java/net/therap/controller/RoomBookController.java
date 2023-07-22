package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Room;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.RoomService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Controller
@RequestMapping("/room/book")
@SessionAttributes("booking")
public class RoomBookController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public String showRoomBookPage(@PathVariable Long id, Model model, HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        if (sessionContext == null) {
            return "redirect:/customer/login";
        }

        Booking booking = Util.createBookingFromSessionContext(sessionContext);
        model.addAttribute("booking", booking);
        model.addAttribute("roomId", id);

        return "roomBookPage";
    }

    @PostMapping("/{roomId}")
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

        bookingService.save(booking);

        return "bookingConfirmationPage";
    }
}
