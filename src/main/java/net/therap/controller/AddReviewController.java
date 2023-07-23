package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Review;
import net.therap.service.BookingService;
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
    public String processAddReview(@PathVariable Long bookingId, @ModelAttribute("booking") @Valid Review review, @PathVariable Long customerId) {

        Booking booking = bookingService.findById(bookingId);

        if (booking.getReview() == null) {

            booking.setReview(review);
            review.setBooking(booking);
            bookingService.merge(booking);
        }

        return "redirect:/booking/" + bookingId;
    }
}
