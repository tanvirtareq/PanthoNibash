package net.therap.validator;

import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class HotelValidator implements Validator {

    @Autowired
    private HotelService hotelService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Hotel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Hotel hotel = (Hotel) target;

        if (hotelService.emailExists(hotel.getEmail())) {
            errors.rejectValue("email", "duplicate.email", "Email already exists");
        }
    }
}
