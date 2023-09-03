package org.tanvir.controller.customer;

import org.tanvir.model.Customer;
import org.tanvir.model.LoginForm;
import org.tanvir.model.SessionContext;
import org.tanvir.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/customer")
public class CustomerLoginController {

    @Autowired
    private CustomerService customerService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "customer/customerLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult bindingResult,
                               HttpSession httpSession) {

        Customer customer = customerService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if (customer == null) {
            bindingResult.reject("credential.error", "Email of Password did not match");
        }

        if (bindingResult.hasErrors()) {
            return "customer/customerLogin";
        }

        SessionContext sessionContext = new SessionContext(customer);
        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/customer/" + customer.getId();
    }
}