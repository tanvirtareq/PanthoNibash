package net.therap.controller;

import net.therap.model.Customer;
import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */

@Controller
@RequestMapping("/hotel/{hotelId}")
public class HotelEditController {

    @Autowired
    private HotelService hotelService;

    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelService.findById(hotelId);

        model.addAttribute("hotel", hotel);
        model.addAttribute("facilityOptions", facilityOptions);

        return "showHotelEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long hotelId, @ModelAttribute("hotel") @Valid Hotel hotel,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("facilityOptions", facilityOptions);
            return "showHotelEditForm";
        }

        hotelService.update(hotelId, hotel.getName(), hotel.getPassword(), hotel.getPhoneNumber(),
                hotel.getLocation(), hotel.getParkingFacility(), hotel.getSwimmingPool(), hotel.getWifiFacility());

        return "redirect:/hotel/"+hotelId;
    }
}
