package net.therap.controller.customer;

import net.therap.constants.PatternConstants;
import net.therap.helper.Helper;
import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.model.SessionContext;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Controller
@RequestMapping("/customer")
@SessionAttributes("sessionContext")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @Autowired
    private Helper helper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable Long id, Model model,
                               @SessionAttribute(required = false) SessionContext sessionContext) {

        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("customerLoggedIn", helper.customerLoggedIn(id, sessionContext));

        return "customer/customerPage";
    }

    @GetMapping("/{customerId}/bookingList")
    public String showBookingList(@PathVariable Long customerId, Model model,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(pattern = PatternConstants.DATE_FORMAT_PATTERN) LocalDate checkInDate,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(pattern = PatternConstants.DATE_FORMAT_PATTERN) LocalDate checkOutDate,
                                  @RequestParam(required = false) String hotelName,
                                  @RequestParam(required = false) String roomType) {

        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);

        List<Booking> bookingList = customerService.findBookingList(customer, checkInDate, checkOutDate,
                hotelName, roomType);

        model.addAttribute("bookingList", bookingList);
        model.addAttribute("roomTypeOptions", roomTypeOptions);

        return "customer/bookingListOfCustomer";
    }
}