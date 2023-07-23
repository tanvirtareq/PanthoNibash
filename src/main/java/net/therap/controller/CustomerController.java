package net.therap.controller;

import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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
 * @since 7/13/23
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        return "customerPage";
    }

    @GetMapping("/{customerId}/bookingList")
    public String showBookingList(@PathVariable Long customerId, Model model,
                                  @RequestParam(value = "checkInDate", required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
                                  @RequestParam(value = "checkOutDate", required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
                                  @RequestParam(value = "hotelName", required = false) String hotelName,
                                  @RequestParam(value = "roomType", required = false) String roomType) {

        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        List<Booking> bookingList = customerService.findBookingList(customer, checkInDate, checkOutDate,
                hotelName, roomType);

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("roomTypeOptions", roomTypeOptions);

        return "bookingListOfCustomer";
    }
}
