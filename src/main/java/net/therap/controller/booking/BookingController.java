package net.therap.controller.booking;

import net.therap.dto.ButtonDto;
import net.therap.model.Booking;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/22/23
 */
@Controller
@RequestMapping("/booking")
@SessionAttributes("sessionContext")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public String showBookingDetails(@PathVariable Long bookingId, Model model,
                                     @SessionAttribute(name = "sessionContext") SessionContext sessionContext) {

        Booking booking = bookingService.findById(bookingId);
        model.addAttribute("booking", booking);

        List<ButtonDto> buttonDtoList = new ArrayList<>();

        if (canAddReview(booking, sessionContext)) {
            buttonDtoList.add(new ButtonDto("Add Review", generateAddReviewLink(booking)));
        }

        if (Util.canCancelBooking(booking, sessionContext)) {
            buttonDtoList.add(new ButtonDto("Cancel Booking", generateCancelBookingLink(booking)));
        }

        model.addAttribute("buttonDtoList", buttonDtoList);

        return "booking/showBookingDetails";
    }

    private String generateCancelBookingLink(Booking booking) {
        return "/hotel/" + booking.getRoom().getHotel().getId() + "/booking/" + booking.getId() + "/cancel";
    }


    private String generateAddReviewLink(Booking booking) {
        return "/customer/" + booking.getCustomer().getId() + "/booking/" + booking.getId() + "/addReview";
    }

    private boolean canAddReview(Booking booking, SessionContext sessionContext) {
        return booking.getReview() == null && sessionContext != null && "CUSTOMER".equals(sessionContext.getRole())
                && sessionContext.getId().equals(booking.getCustomer().getId())
                && booking.getCheckOutDate().isBefore(LocalDate.now());
    }
}