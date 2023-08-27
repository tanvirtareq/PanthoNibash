package net.therap.util;

import net.therap.model.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
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

        if (!Arrays.asList(allowedExtensions).contains(extension.toLowerCase())) {
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

    public static void updateHotelRating(Hotel hotel, Booking booking, Review review) {

        double newRating = review.getRating();
        double newWeight = Util.getRatingWeight(booking);

        Rating oldRating = hotel.getRating();

        if (oldRating == null) {
            Rating rating = new Rating();
            rating.setRating(newRating);
            rating.setWeight(newWeight);

            hotel.setRating(rating);

        } else {

            oldRating.setRating(Util.calculateRating(oldRating, newRating, newWeight));
            oldRating.setWeight(oldRating.getWeight() + newWeight);
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
        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole())
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

    public static String generateCancelBookingLink(Booking booking) {
        return "hotel/" + booking.getRoom().getHotel().getId() + "/booking/" + booking.getId() + "/cancel";
    }

    public static String generateAddReviewLink(Booking booking) {
        return "/booking/" + booking.getId() + "/addReview";
    }

    public static boolean canAddReview(Booking booking, SessionContext sessionContext) {
        return booking.getReview() == null && sessionContext != null && Role.CUSTOMER.equals(sessionContext.getRole())
                && sessionContext.getId().equals(booking.getCustomer().getId())
                && booking.getCheckOutDate().isBefore(LocalDate.now());
    }

    public static boolean canUpdateBookingCheckoutDate(Booking booking, SessionContext sessionContext) {
        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole())
                && sessionContext.getId().equals(booking.getRoom().getHotel().getId())
                && booking.getCheckOutDate().isAfter(LocalDate.now());
    }

    public static String generateUpdateBookingCheckoutDate(Booking booking) {
        return "hotel/" + booking.getRoom().getHotel().getId() + "/booking/" + booking.getId()
                + "/update-booking-checkout-date";
    }

    public static String generateCustomerBookingListUrl(Long customerId) {
        return "/customer/" + customerId + "/bookingList";
    }

    public static String generateCustomerProfileUrl(Long customerId) {
        return "/customer/" + customerId;
    }

    public static Long getCustomerIdFromRequest(HttpServletRequest request) {
        return getNumberFromPatternRequest("customer/(\\d+)", request);
    }

    public static Long getNumberFromPatternRequest(String pattern, HttpServletRequest request) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(request.getRequestURI());

        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }

        return null;
    }

    public static Long getBookingIdFromRequest(HttpServletRequest request) {
        return getNumberFromPatternRequest("booking/(\\d+)", request);
    }

    public static Long getHotelIdFromRequest(HttpServletRequest request) {
        return getNumberFromPatternRequest("hotel/(\\d+)", request);
    }
}