package net.therap.controller.hotel;

import net.therap.model.Hotel;
import net.therap.model.LoginForm;
import net.therap.model.SessionContext;
import net.therap.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());

        return "hotel/hotelLogin";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult,
                               HttpSession httpSession) {

        Hotel hotel = hotelService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

        if (hotel == null) {
            bindingResult.reject("credential.error", "Email of Password did not match");
        }

        if (bindingResult.hasErrors()) {
            return "hotel/hotelLogin";
        }

        SessionContext sessionContext = new SessionContext(hotel);
        httpSession.setAttribute("sessionContext", sessionContext);

        return "redirect:/hotel/" + hotel.getId();
    }
}