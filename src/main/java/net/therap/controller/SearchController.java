package net.therap.controller;

import net.therap.dto.SearchRoomFilter;
import net.therap.model.Room;
import net.therap.model.RoomType;
import net.therap.service.HotelService;
import net.therap.service.RoomService;
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