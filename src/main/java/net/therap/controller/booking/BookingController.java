package net.therap.controller.booking;

import net.therap.dto.ButtonDto;
import net.therap.model.Booking;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/booking")
@SessionAttributes("sessionContext")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{bookingId}")
    public String showBookingDetails(@PathVariable Long bookingId, Model model,
                                     @SessionAttribute(name = "sessionContext") SessionContext sessionContext,
                                     HttpServletRequest request) {

        Booking booking = bookingService.findById(bookingId);
        model.addAttribute("booking", booking);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale = localeResolver.resolveLocale(request);

        if (Util.canAddReview(booking, sessionContext)) {
            String addReviewLabel = messageSource.getMessage("button.addReview.label", null, locale);
            buttonDtoList.add(new ButtonDto(addReviewLabel, Util.generateAddReviewLink(booking)));
        }

        if (Util.canCancelBooking(booking, sessionContext)) {
            String cancelBookingLabel = messageSource.getMessage("button.cancelBooking.label", null, locale);
            buttonDtoList.add(new ButtonDto(cancelBookingLabel, Util.generateCancelBookingLink(booking)));
        }

        if (Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            String updateCheckoutDateLabel = messageSource.getMessage("button.updateCheckoutDate.label", null, locale);
            buttonDtoList.add(new ButtonDto(updateCheckoutDateLabel, Util.generateUpdateBookingCheckoutDate(booking)));
        }

        model.addAttribute("buttonDtoList", buttonDtoList);

        return "booking/showBookingDetails";
    }
}
