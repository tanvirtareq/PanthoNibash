package net.therap.util;

import net.therap.model.*;
import net.therap.service.CustomerService;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
public class Util {

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

    public static Booking createBookingFromSessionContext(SessionContext sessionContext, CustomerService customerService) {

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

    public static void updateHotelRating(Hotel hotel, Booking booking, Review review) {

        double newRating = review.getRating();
        double newWeight = Util.getRatingWeight(booking);
        System.out.println(newRating + "   " + newWeight);

        if (hotel.getRating() == null) {
            Rating rating = new Rating();
            rating.setRating(newRating);
            rating.setWeight(newWeight);

            hotel.setRating(rating);

        } else {

            hotel.getRating().setRating(Util.calculateRating(hotel.getRating(), newRating, newWeight));
            hotel.getRating().setWeight(hotel.getRating().getWeight() + newWeight);
        }
    }

    private static double calculateRating(Rating rating, double newRating, double newWeight) {

        double oldRating = rating.getRating();
        double oldWeight = rating.getWeight();

        return (oldRating * oldWeight + newRating * newWeight) / (oldWeight + newWeight);
    }

    private static double getRatingWeight(Booking booking) {

        double numberOfDaysStaying = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate()) + 1;
        double yearOfCheckIngDate = booking.getCheckInDate().getYear();
        double yearOfHotelRegistration = booking.getRoom().getHotel().getCreatedAt().getYear();

        double weight = Math.sqrt(numberOfDaysStaying * (yearOfCheckIngDate - yearOfHotelRegistration + 1) / 1000.0);

        return weight;
    }

    public static boolean canCancelBooking(Booking booking, SessionContext sessionContext) {
        return sessionContext != null && "HOTEL".equals(sessionContext.getRole())
                && sessionContext.getId().equals(booking.getRoom().getHotel().getId())
                && booking.getCheckInDate().isAfter(LocalDate.now());
    }

    public static String generateHotelProfileUrl(Long hotelId) {
        return "/hotel/" + hotelId;
    }

    public static String generateBookingListUrl(Long hotelId) {
        return "/hotel/" + hotelId + "/bookingList";
    }

    public static String generateBookingDetailsUrl(Long bookingId) {
        return "/booking/" + bookingId;
    }

    public static String generateRoomUrl(Long roomId) {
        return "/room/" + roomId;
    }

    public static String generateRoomBookUrl(Long roomId) {
        return "/room/" + roomId + "/book";
    }
}