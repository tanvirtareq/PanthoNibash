package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Review;
import net.therap.service.BookingService;
import net.therap.service.HotelService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/addreview")
    public String showAddReviewForm(@PathVariable Long bookingId, Model model, @PathVariable Long customerId) {

        Booking booking = bookingService.findById(bookingId);

        if (booking.getReview() != null) {
            return "redirect:/booking/" + bookingId;
        }

        model.addAttribute("review", new Review());

        return "addReviewForm";
    }

    @PostMapping("/addreview")
    public String processAddReview(@PathVariable Long bookingId, @ModelAttribute("booking") @Valid Review review, @PathVariable Long customerId) {

        Booking booking = bookingService.findById(bookingId);

        if (booking.getReview() == null) {

            booking.setReview(review);

            Util.updateHotelRating(booking.getRoom().getHotel(), booking, review);

            bookingService.merge(booking);
            hotelService.merge(booking.getRoom().getHotel());
        }

        return "redirect:/booking/" + bookingId;
    }
}
