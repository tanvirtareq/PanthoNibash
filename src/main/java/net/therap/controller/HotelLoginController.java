package net.therap.controller;

import net.therap.model.Hotel;
import net.therap.model.LoginForm;
import net.therap.model.SessionContext;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/hotel")
public class HotelLoginController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "hotelLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult bindingResult,
                               Model model, HttpSession httpSession) {

        if (hotelService.findByEmail(loginForm.getEmail()) == null) {
            bindingResult.rejectValue("email", "email.noEmail", "Email is not registered");
        }

        if (bindingResult.hasErrors()) {
            return "hotelLogin";
        }

        Hotel hotel = hotelService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if (hotel == null) {
            bindingResult.rejectValue("credential.error", "Email of Password did not match");
        }

        if (bindingResult.hasErrors()) {
            return "hotelLogin";
        }

        SessionContext sessionContext = new SessionContext(hotel, hotel.getEmail(), "HOTEL",
                hotel.getId(), hotel.getName(), "/hotel/" + hotel.getId(),
                hotel.getHotelImageBase64Image());

        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/hotel/" + hotel.getId();
    }

}
