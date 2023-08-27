package net.therap.controller.hotel;

import net.therap.dto.ButtonDto;
import net.therap.dto.DoneMessageDto;
import net.therap.helper.Helper;
import net.therap.model.Hotel;
import net.therap.service.HotelService;
import net.therap.util.Util;
import net.therap.validator.HotelValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private Helper helper;

    @InitBinder("hotel")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
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
    public String signup(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult, Model model,
                         HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("facilityOptions", facilityOptions);

            return "hotel/hotelSignup";
        }

        helper.imageUploadHelper(model, request, "hotel.imageUpload.title",
                "hotel/signup/hotelImageUpload");

        return "imageUpload";
    }

    @PostMapping("/signup/hotelImageUpload")
    public String uploadProfilePicture(@RequestParam CommonsMultipartFile image,
                                       @SessionAttribute Hotel hotel, Model model,
                                       HttpServletRequest request) {

        if (image.isEmpty()) {
            model.addAttribute("error", "Please select a Hotel Image.");
            helper.imageUploadHelper(model, request, "hotel.imageUpload.title",
                    "hotel/signup/hotelImageUpload");

            return "imageUpload";
        }

        String extension = FilenameUtils.getExtension(image.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            helper.imageUploadHelper(model, request, "hotel.imageUpload.title",
                    "hotel/signup/hotelImageUpload");

            return "imageUpload";
        }

        try {
            byte[] hotelImageData = IOUtils.toByteArray(image.getInputStream());
            hotel.setHotelImage(hotelImageData);
            hotel.setPassword(hotelService.encodePassword(hotel.getPassword()));
            hotelService.save(hotel);
            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("Go to login", "/hotel/login"));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            DoneMessageDto doneMessageDto = new DoneMessageDto("Successfully Registered",
                    buttonDtoList, helper.getMessageFromMessageCode("label.success", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Hotel Image.");
            helper.imageUploadHelper(model, request, "hotel.imageUpload.title",
                    "hotel/signup/hotelImageUpload");

            return "imageUpload";
        }
    }
}