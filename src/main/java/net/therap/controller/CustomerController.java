package net.therap.controller;

import net.therap.model.Customer;
import net.therap.model.LoginForm;
import net.therap.model.SessionContext;
import net.therap.service.CustomerService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Controller
@RequestMapping("/customer")
@SessionAttributes({"customer"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "customerSignup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {

        if (customerService.findByEmail(customer.getEmail()) != null) {
            bindingResult.rejectValue("email", "duplicate.email", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "customerSignup";
        }

        return "profilePictureUpload";
    }

    @PostMapping("/uploadProfilePicture")
    public String uploadProfilePicture(@RequestParam("profilePicture") CommonsMultipartFile profilePicture,
                                       @SessionAttribute(name = "customer") Customer customer, Model model) {


        if (profilePicture.isEmpty()) {
            model.addAttribute("error", "Please select a profile picture.");

            return "profilePictureUpload";
        }
        String[] allowedExtensions = {"jpg", "jpeg", "png"};
        String extension = FilenameUtils.getExtension(profilePicture.getOriginalFilename());

        if (!Arrays.asList(allowedExtensions).contains(extension)) {
            model.addAttribute("error", "Only JPG, JPEG and PNG files are allowed.");

            return "profilePictureUpload";
        }

        try {
            byte[] profilePictureData = IOUtils.toByteArray(profilePicture.getInputStream());
            customer.setProfilePicture(profilePictureData);
            customerService.save(customer);

            return "redirect:/customer/login";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the profile picture.");
            return "profilePictureUpload";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "customerLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult bindingResult,
                               Model model, HttpSession httpSession) {
        if(customerService.findByEmail(loginForm.getEmail()) == null) {
            bindingResult.rejectValue("email", "email.noEmail", "Email is not registered");
        }

        if(bindingResult.hasErrors()) {
            return "customerLogin";
        }

        Customer customer = customerService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if(customer == null) {
            bindingResult.rejectValue("credential.error", "Email of Password did not match");
        }

        if(bindingResult.hasErrors()) {
            return "customerLogin";
        }

        SessionContext sessionContext = new SessionContext(customer, customer.getEmail(), "CUSTOMER",
                customer.getId(), customer.getName(), "/customer/"+customer.getId(),
                customer.getProfilePicBase64Image());

        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/customer/"+ customer.getId();
    }

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {

        Customer customer = customerService.findById(id);

        model.addAttribute("customer", customer);

        return "customerPage";
    }
}
