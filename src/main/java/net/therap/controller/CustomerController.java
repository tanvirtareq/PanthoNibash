package net.therap.controller;

import net.therap.model.Customer;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        return "customerPage";
    }
}
