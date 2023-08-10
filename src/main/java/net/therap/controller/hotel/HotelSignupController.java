package net.therap.controller.hotel;

import net.therap.model.Hotel;
import net.therap.service.HotelService;
import net.therap.util.Util;
import net.therap.validator.HotelValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @Autowired
    private HotelValidator hotelValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(hotelValidator);
        binder.setDisallowedFields("id", "createdAt");
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("facilityOptions", facilityOptions);

        return "hotel/hotelSignup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("facilityOptions", facilityOptions);

            return "hotel/hotelSignup";
        }

        return "hotel/hotelImageUpload";
    }

    @PostMapping("/signup/hotelImageUpload")
    public String uploadProfilePicture(@RequestParam("hotelImage") CommonsMultipartFile hotelImage,
                                       @SessionAttribute(name = "hotel") Hotel hotel, Model model) {


        if (hotelImage.isEmpty()) {
            model.addAttribute("error", "Please select a Hotel Image.");

            return "hotel/hotelImageUpload";
        }

        String extension = FilenameUtils.getExtension(hotelImage.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            return "hotel/hotelImageUpload";
        }

        try {
            byte[] hotelImageData = IOUtils.toByteArray(hotelImage.getInputStream());
            hotel.setHotelImage(hotelImageData);

            hotel.setPassword(hotelService.encodePassword(hotel.getPassword()));

            hotelService.save(hotel);

            return "redirect:/hotel/login";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Hotel Image.");
            return "hotel/hotelImageUpload";
        }
    }
}