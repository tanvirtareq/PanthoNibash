package net.therap.validator;

import net.therap.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * @author tanvirtareq
 * @since 8/16/23
 */
@Component
public class BookingValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Booking.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Booking booking = (Booking) target;

        if (booking.getCheckInDate() != null && booking.getCheckOutDate() != null
                && !booking.getCheckInDate().isBefore(booking.getCheckOutDate())) {

            errors.rejectValue("checkOutDate", "invalidCheckinCheckout",
                    "Check out date should be after check in date");
        }

        if (booking.getCheckInDate() != null && booking.getCheckInDate().isBefore(LocalDate.now())) {
            errors.rejectValue("checkInDate", "invalidCheckin", "Check in date can't be before current date");
        }
    }
}
