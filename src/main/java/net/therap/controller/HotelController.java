package net.therap.controller;

import net.therap.model.Customer;
import net.therap.model.Hotel;
import net.therap.model.LoginForm;
import net.therap.model.SessionContext;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */
@Controller
@RequestMapping("/hotel")
@SessionAttributes({"hotel"})
public class HotelController {
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
    public String signup(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult,Model model) {

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

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "hotelLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult bindingResult,
                               Model model, HttpSession httpSession) {
        if(hotelService.findByEmail(loginForm.getEmail()) == null) {
            bindingResult.rejectValue("email", "email.noEmail", "Email is not registered");
        }

        if(bindingResult.hasErrors()) {
            return "hotelLogin";
        }

        Hotel hotel = hotelService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if(hotel == null) {
            bindingResult.rejectValue("credential.error", "Email of Password did not match");
        }

        if(bindingResult.hasErrors()) {
            return "hotelLogin";
        }

        SessionContext sessionContext = new SessionContext(hotel, hotel.getEmail(), "HOTEL",
                hotel.getId(), hotel.getName(), "/hotel/"+hotel.getId(),
                hotel.getHotelImageBase64Image());

        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/hotel/"+ hotel.getId();
    }


    @GetMapping("/{id}")
    public String showHotel(@PathVariable Long id, Model model) {

        Hotel hotel = hotelService.findById(id);

        model.addAttribute("hotel", hotel);

        return "hotelPage";
    }
}
