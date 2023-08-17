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

        if (Util.canAddReview(booking, sessionContext)) {
            buttonDtoList.add(new ButtonDto("Add Review", Util.generateAddReviewLink(booking)));
        }

        if (Util.canCancelBooking(booking, sessionContext)) {
            buttonDtoList.add(new ButtonDto("Cancel Booking", Util.generateCancelBookingLink(booking)));
        }

        if (Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            buttonDtoList.add(new ButtonDto("Update Checkout Date",
                    Util.generateUpdateBookingCheckoutDate(booking)));
        }

        model.addAttribute("buttonDtoList", buttonDtoList);

        return "booking/showBookingDetails";
    }
}