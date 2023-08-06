package net.therap.controller.customer;

import net.therap.model.Customer;
import net.therap.model.LoginForm;
import net.therap.model.SessionContext;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        SessionContext sessionContext = new SessionContext(customer.getEmail(), "CUSTOMER",
                customer.getId(), customer.getName(), "/customer/" + customer.getId(),
                customer.getProfilePicBase64Image());

        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/customer/" + customer.getId();
    }
}