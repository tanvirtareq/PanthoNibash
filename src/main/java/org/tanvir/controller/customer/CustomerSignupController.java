package org.tanvir.controller.customer;

import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.helper.Helper;
import org.tanvir.model.Customer;
import org.tanvir.service.CustomerService;
import org.tanvir.util.Util;
import org.tanvir.validator.CustomerValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private Helper helper;

    @InitBinder("customer")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(customerValidator);
        binder.setDisallowedFields("id");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer/customerSignup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult,
                         Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "customer/customerSignup";
        }

        helper.imageUploadHelper(model, request, "profilePicture.upload.title",
                "customer/signup/uploadProfilePicture");

        return "imageUpload";
    }

    @PostMapping("/signup/uploadProfilePicture")
    public String uploadProfilePicture(@RequestParam CommonsMultipartFile image,
                                       @SessionAttribute(name = "customer") Customer customer, Model model,
                                       HttpServletRequest request) {


        if (image.isEmpty()) {
            helper.imageUploadHelper(model, request, "profilePicture.upload.title",
                    "customer/signup/uploadProfilePicture");

            model.addAttribute("error", "Please select a profile picture.");

            return "imageUpload";
        }

        String extension = FilenameUtils.getExtension(image.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            helper.imageUploadHelper(model, request, "profilePicture.upload.title",
                    "customer/signup/uploadProfilePicture");

            return "imageUpload";
        }

        try {
            byte[] profilePictureData = IOUtils.toByteArray(image.getInputStream());
            customer.setProfilePicture(profilePictureData);
            customer.setPassword(customerService.encodePassword(customer.getPassword()));
            customerService.save(customer);

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("Go to login", "/customer/login"));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            DoneMessageDto doneMessageDto = new DoneMessageDto("Successfully Registered", buttonDtoList,
                    helper.getMessageFromMessageCode("label.success", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";

        } catch (IOException e) {
            helper.imageUploadHelper(model, request, "profilePicture.upload.title",
                    "customer/signup/uploadProfilePicture");

            model.addAttribute("error", "Failed to upload the profile picture.");

            return "imageUpload";
        }
    }
}