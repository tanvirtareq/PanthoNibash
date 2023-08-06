package net.therap.controller.hotel;

import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/25/23
 */
@Controller
public class HotelsController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public String showHotels(Model model) {
        List<Hotel> hotels = hotelService.getAll();
        model.addAttribute("hotels", hotels);

        return "hotel/showHotels";
    }
}
