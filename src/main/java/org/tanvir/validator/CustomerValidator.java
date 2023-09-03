package org.tanvir.validator;

import org.tanvir.model.Customer;
import org.tanvir.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class CustomerValidator implements Validator {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        if (customerService.findByEmail(customer.getEmail()) != null) {
            errors.rejectValue("email", "duplicate.email", "Email already exists");
        }
    }
}
