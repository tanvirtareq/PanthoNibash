package org.tanvir.controller;

import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.helper.Helper;
import org.tanvir.model.Booking;
import org.tanvir.model.Hotel;
import org.tanvir.model.Review;
import org.tanvir.model.SessionContext;
import org.tanvir.service.BookingService;
import org.tanvir.service.HotelService;
import org.tanvir.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/22/23
 */
@Controller
@RequestMapping("/booking/{bookingId}")
@SessionAttributes("sessionContext")
public class AddReviewController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private Helper helper;

    @GetMapping("/addReview")
    public String showAddReviewForm(@PathVariable Long bookingId, Model model,
                                    @SessionAttribute(required = false) SessionContext sessionContext) {

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canAddReview(booking, sessionContext)) {
            return "redirect:/booking/" + bookingId;
        }

        model.addAttribute("review", new Review());

        return "addReviewForm";
    }

    @PostMapping("/addReview")
    public String processAddReview(@PathVariable Long bookingId, @ModelAttribute @Valid Review review,
                                   BindingResult bindingResult, Model model, HttpServletRequest request,
                                   @SessionAttribute(required = false) SessionContext sessionContext) {

        Booking booking = bookingService.findById(bookingId);

        if (booking.getCustomer() == null || helper.customerLoggedIn(booking.getCustomer().getId(), sessionContext)) {
            return "redirect:/booking/" + bookingId;
        }

        if (bindingResult.hasErrors()) {
            return "addReviewForm";
        }

        if (booking.getReview() == null) {
            Hotel hotel = booking.getRoom().getHotel();
            booking.setReview(review);
            Util.updateHotelRating(hotel, booking, review);
            bookingService.merge(booking);
            hotelService.merge(hotel);

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + hotel.getId()));
            buttonDtoList.add(new ButtonDto("See Room Details", "/room/" + booking.getRoom().getId()));
            buttonDtoList.add(new ButtonDto("See Booking Details", "/booking/" + booking.getId()));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            DoneMessageDto doneMessageDto = new DoneMessageDto("Thank You for your feedback", buttonDtoList,
                    helper.getMessageFromMessageCode("label.success", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";
        }

        return "redirect:/booking/" + bookingId;
    }
}