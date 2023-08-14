package net.therap.controller;

import net.therap.dto.ButtonDto;
import net.therap.dto.SuccessMessageDto;
import net.therap.model.Booking;
import net.therap.model.Review;
import net.therap.service.BookingService;
import net.therap.service.HotelService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/22/23
 */
@Controller
@RequestMapping("/customer/{customerId}/booking/{bookingId}")
public class AddReviewController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/addReview")
    public String showAddReviewForm(@PathVariable Long bookingId, Model model, @PathVariable Long customerId) {

        Booking booking = bookingService.findById(bookingId);

        if (booking.getReview() != null) {
            return "redirect:/booking/" + bookingId;
        }

        model.addAttribute("review", new Review());

        return "addReviewForm";
    }

    @PostMapping("/addReview")
    public String processAddReview(@PathVariable Long bookingId, @ModelAttribute("review") @Valid Review review,
                                   BindingResult bindingResult, @PathVariable Long customerId, Model model) {

        Booking booking = bookingService.findById(bookingId);

        if (bindingResult.hasErrors()) {
            return "addReviewForm";
        }

        if (booking.getReview() == null) {

            booking.setReview(review);

            Util.updateHotelRating(booking.getRoom().getHotel(), booking, review);

            bookingService.merge(booking);
            hotelService.merge(booking.getRoom().getHotel());


            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + booking.getRoom().getHotel().getId()));
            buttonDtoList.add(new ButtonDto("See Room Details", "/room/" + booking.getRoom().getId()));
            buttonDtoList.add(new ButtonDto("See Booking Details", "/booking/" + booking.getId()));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            SuccessMessageDto successMessageDto = new SuccessMessageDto("Thank You for your feedback", buttonDtoList);

            model.addAttribute("successMessageDto", successMessageDto);

            return "successMessage";
        }

        return "redirect:/booking/" + bookingId;
    }
}