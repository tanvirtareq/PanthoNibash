package net.therap.controller.hotel;

import net.therap.dto.ButtonDto;
import net.therap.dto.DoneMessageDto;
import net.therap.helper.Helper;
import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */
@Controller
@RequestMapping("/hotel/{hotelId}")
@SessionAttributes("hotel")
public class HotelEditController {

    @Autowired
    private HotelService hotelService;

    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;

    @Autowired
    private Helper helper;

    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId);

        model.addAttribute("hotel", hotel);
        model.addAttribute("facilityOptions", facilityOptions);

        return "hotel/showHotelEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long hotelId, @ModelAttribute("hotel") @Valid Hotel hotel,
                                  BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("facilityOptions", facilityOptions);

            return "hotel/showHotelEditForm";
        }

        hotelService.merge(hotel);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + hotelId));
        buttonDtoList.add(new ButtonDto("Go to Home", "/"));

        DoneMessageDto doneMessageDto = new DoneMessageDto("Information Successfully Updated",
                buttonDtoList, helper.getMessageFromMessageCode("label.success", request));

        model.addAttribute("doneMessageDto", doneMessageDto);

        return "doneMessage";
    }
}