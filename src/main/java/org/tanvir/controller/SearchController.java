package org.tanvir.controller;

import org.tanvir.dto.SearchRoomFilter;
import org.tanvir.model.Room;
import org.tanvir.model.RoomType;
import org.tanvir.service.HotelService;
import org.tanvir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private HotelService hotelService;

    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;

    @ModelAttribute("roomTypeOptions")
    public RoomType[] roomTypeOptions() {
        return RoomType.values();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/search")
    public String searchRooms(@ModelAttribute("searchRoomFilter") SearchRoomFilter searchRoomFilter,
                              Model model) {

        if (searchRoomFilter == null) {
            searchRoomFilter = new SearchRoomFilter();
        }

        List<Room> searchResults = roomService.searchRooms(searchRoomFilter);

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("facilityOptions", facilityOptions);
        model.addAttribute("searchRoomFilter", new SearchRoomFilter());

        return "search";
    }

    @GetMapping("/search/hotels")
    @ResponseBody
    public List<String> searchHotels(@RequestParam(value = "name", required = false) String hotelName) {
        List<String> matchingHotels = hotelService.getHotelsNameByPartialName(hotelName);

        return matchingHotels;
    }
}