package net.therap.controller;

import net.therap.model.Customer;
import net.therap.service.CustomerService;
import net.therap.util.Util;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/customer")
@SessionAttributes({"customer"})
public class CustomerSignupController {

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

        String extension = FilenameUtils.getExtension(profilePicture.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
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
}
