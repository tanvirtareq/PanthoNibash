package net.therap.controller.customer;

import net.therap.dto.ButtonDto;
import net.therap.dto.DoneMessageDto;
import net.therap.helper.Helper;
import net.therap.model.Customer;
import net.therap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */
@Controller
@RequestMapping("/customer/{customerId}")
@SessionAttributes("customer")
public class CustomerEditController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Helper helper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customer/showCustomerEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long customerId, @ModelAttribute @Validated Customer customer,
                                  BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "customer/showCustomerEditForm";
        }

        customerService.merge(customer);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("Go to profile", "/customer/" + customerId));
        buttonDtoList.add(new ButtonDto("Go to Home", "/"));

        DoneMessageDto doneMessageDto = new DoneMessageDto("Information Successfully Updated", buttonDtoList,
                helper.getMessageFromMessageCode("label.success", request));

        model.addAttribute("doneMessageDto", doneMessageDto);

        return "doneMessage";
    }
}