package net.therap.controller;

import net.therap.dto.SearchRoomFilter;
import net.therap.model.Room;
import net.therap.service.HotelService;
import net.therap.service.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
public class SearchController {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class);

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Value("#{facilityOptions}")
    private Map<String, String> facilityOptions;
    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/search")
    public String searchRooms(@ModelAttribute("searchRoomFilter") SearchRoomFilter searchRoomFilter,
                              Model model) {

        LOGGER.info("Search started");

        if (searchRoomFilter == null) {
            searchRoomFilter = new SearchRoomFilter();
        }

        List<Room> searchResults = roomService.searchRooms(searchRoomFilter);

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("facilityOptions", facilityOptions);
        model.addAttribute("roomTypeOptions", roomTypeOptions);
        model.addAttribute("searchRoomFilter", new SearchRoomFilter());

        LOGGER.info("Search end");
        return "search";
    }

    @GetMapping("/search/hotels")
    @ResponseBody
    public List<String> searchHotels(@RequestParam(value = "name", required = false) String hotelName) {
        List<String> matchingHotels = hotelService.getHotelsNameByPartialName(hotelName);

        return matchingHotels;
    }
}