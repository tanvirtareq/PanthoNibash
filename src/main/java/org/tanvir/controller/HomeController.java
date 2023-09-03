package org.tanvir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/search";
    }
}
