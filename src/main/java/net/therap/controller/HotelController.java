package net.therap.controller;

import net.therap.model.*;
import net.therap.service.HotelService;
import net.therap.util.Util;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
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

    @GetMapping("/{id}")
    public String showHotel(@PathVariable Long id, Model model) {

        Hotel hotel = hotelService.findById(id);

        model.addAttribute("hotel", hotel);

        return "hotelPage";
    }

}
