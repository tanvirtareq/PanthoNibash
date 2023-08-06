package net.therap.controller.customer;

import net.therap.model.Customer;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */
@Controller
@RequestMapping("/customer/{customerId}")
public class CustomerEditController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long customerId, Model model) {

        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);

        return "customer/showCustomerEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long customerId, @ModelAttribute("customer") @Valid Customer customer,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer/showCustomerEditForm";
        }

        customerService.update(customerId, customer.getName(), customer.getPhoneNumber(),
                customer.getDateOfBirth());

        return "redirect:/customer/" + customerId;
    }
}