package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Hotel;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/{id}")
    public String showHotel(@PathVariable Long id, Model model) {

        Hotel hotel = hotelService.findById(id);

        model.addAttribute("hotel", hotel);

        return "hotelPage";
    }

    @GetMapping("/{hotelId}/booking/list")
    public String showBookingList(@PathVariable Long hotelId, Model model,
                                  @RequestParam(value = "checkInDate", required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
                                  @RequestParam(value = "checkOutDate", required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
                                  @RequestParam(value = "roomNumber", required = false) String roomNumber,
                                  @RequestParam(value = "customerName", required = false) String customerName,
                                  @RequestParam(value = "customerEmail", required = false) String customerEmail,
                                  @RequestParam(value = "roomType", required = false) String roomType) {

        Hotel hotel = hotelService.findById(hotelId);
        model.addAttribute("hotel", hotel);
        List<Booking> bookingList = hotelService.findBookingList(hotel, checkInDate, checkOutDate, roomNumber,
                customerName, customerEmail, roomType);

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("roomTypeOptions", roomTypeOptions);

        return "bookingListOfHotel";
    }
}
