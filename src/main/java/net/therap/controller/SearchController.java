package net.therap.controller;

import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
public class SearchController {

    @Autowired
    private RoomService roomService;

    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/search")
    public String searchRooms(@RequestParam(value = "parkingFacility", required = false) String parkingFacility,
                              @RequestParam(value = "wifiFacility", required = false) String wifiFacility,
                              @RequestParam(value = "swimmingPool", required = false) String swimmingPool,
                              @RequestParam(value = "fitnessCentre", required = false) String fitnessCentre,
                              @RequestParam(value = "roomType", required = false) String roomType,
                              @RequestParam(value = "hotelName", required = false) String hotelName,
                              @RequestParam(value = "location", required = false) String location,
                              @RequestParam(value = "priceMin", required = false) Integer priceMin,
                              @RequestParam(value = "priceMax", required = false) Integer priceMax,
                              @RequestParam(value = "numberOfBed", required = false) Integer numberOfBed,
                              @RequestParam(value = "checkIn", required = false)
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkIn,
                              @RequestParam(value = "checkOut", required = false)
                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOut,
                              Model model) {

        List<Room> searchResults = roomService.searchRooms(parkingFacility, wifiFacility, fitnessCentre, swimmingPool,
                roomType, hotelName, location, priceMin, priceMax, numberOfBed, checkIn, checkOut);

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("facilityOptions", facilityOptions);
        model.addAttribute("roomTypeOptions", roomTypeOptions);

        return "search";
    }
}