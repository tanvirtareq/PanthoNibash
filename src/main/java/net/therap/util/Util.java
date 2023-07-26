package net.therap.util;

import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.model.SessionContext;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
public class Util {

    @Autowired
    private static CustomerService customerService;

    public static boolean allowedImageExtension(String extension, Model model) {

        String[] allowedExtensions = {"jpg", "jpeg", "png", "webp", "avif"};

        if (!Arrays.asList(allowedExtensions).contains(extension)) {
            model.addAttribute("error", "Only JPG, JPEG, WEBP, AVIF and PNG files are allowed.");

            return false;
        }
        return true;
    }

    public static boolean partialMatch(String name, String patternName) {

        Pattern pattern = Pattern.compile(patternName, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    public static Booking createBookingFromSessionContext(SessionContext sessionContext) {

        Booking booking = new Booking();

        if (sessionContext.getRole().equals("CUSTOMER")) {

            Customer customer = customerService.findById(sessionContext.getId());
            booking.setCustomer(customer);
            booking.setGuestName(customer.getName());
            booking.setGuestEmail(customer.getEmail());
            booking.setGuestPhoneNumber(customer.getPhoneNumber());
        }

        return booking;
    }
}