package net.therap.controller;

import net.therap.model.Booking;
import net.therap.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanvirtareq
 * @since 7/22/23
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public String showBookingDetails(@PathVariable Long bookingId, Model model) {

        Booking booking = bookingService.findById(bookingId);
        model.addAttribute("booking", booking);

        return "showBookingDetails";
    }
}