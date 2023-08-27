package net.therap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author tanvirtareq
 * @since 7/22/23
 */
@Controller
@SessionAttributes("sessionContext")
public class LogoutController {

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        return "redirect:search";
    }
}
