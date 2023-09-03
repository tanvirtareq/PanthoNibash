package org.tanvir.controller.hotel;

import org.tanvir.model.Hotel;
import org.tanvir.service.HotelService;
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
