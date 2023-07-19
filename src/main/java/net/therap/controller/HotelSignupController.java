package net.therap.controller;

import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/hotel")
@SessionAttributes({"hotel"})
public class HotelSignupController {

    @Autowired
    private HotelService hotelService;

    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("facilityOptions", facilityOptions);

        return "hotelSignup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult, Model model) {

        if (hotelService.findByEmail(hotel.getEmail()) != null) {
            bindingResult.rejectValue("email", "duplicate.email", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("facilityOptions", facilityOptions);

            return "hotelSignup";
        }

        return "hotelImageUpload";
    }

    @PostMapping("/hotelImageUpload")
    public String uploadProfilePicture(@RequestParam("hotelImage") CommonsMultipartFile hotelImage,
                                       @SessionAttribute(name = "hotel") Hotel hotel, Model model) {


        if (hotelImage.isEmpty()) {
            model.addAttribute("error", "Please select a Hotel Image.");

            return "hotelImageUpload";
        }
        String[] allowedExtensions = {"jpg", "jpeg", "png"};
        String extension = FilenameUtils.getExtension(hotelImage.getOriginalFilename());

        if (!Arrays.asList(allowedExtensions).contains(extension)) {
            model.addAttribute("error", "Only JPG, JPEG and PNG files are allowed.");

            return "hotelImageUpload";
        }

        try {
            byte[] hotelImageData = IOUtils.toByteArray(hotelImage.getInputStream());
            hotel.setHotelImage(hotelImageData);
            System.out.println(hotel);

            hotelService.save(hotel);

            return "redirect:/hotel/login";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Hotel Image.");
            return "hotelImageUpload";
        }
    }
}
