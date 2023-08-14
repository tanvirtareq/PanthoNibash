package net.therap.controller.room;

import net.therap.model.Room;
import net.therap.service.HotelService;
import net.therap.util.Util;
import net.therap.validator.RoomNumberValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/hotel/{hotelId}")
@SessionAttributes({"room"})
public class HotelAddRoomController {

    private final Logger LOGGER = Logger.getLogger(HotelAddRoomController.class);

    @Autowired
    private HotelService hotelService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @Autowired
    private RoomNumberValidator roomNumberValidator;

    @GetMapping("/addRoom")
    public String addRoom(@PathVariable Long hotelId, Model model) {

        model.addAttribute("room", new Room());
        model.addAttribute("roomTypeOptions", roomTypeOptions);
        model.addAttribute("hotelId", hotelId);

        return "room/addRoom";
    }

    @PostMapping("/addRoom")
    public String processAddRoom(@PathVariable Long hotelId, @ModelAttribute @Valid Room room,
                                 BindingResult bindingResult, Model model) {

        LOGGER.info(room);

        roomNumberValidator.validateRoomNumbers(hotelId, room, bindingResult);

        LOGGER.info(bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("roomTypeOptions", roomTypeOptions);
            model.addAttribute("hotelId", hotelId);

            return "room/addRoom";
        }

        model.addAttribute("hotelId", hotelId);

        return "room/roomImageUpload";
    }

    @PostMapping("/addroom/roomImageUpload")
    public String uploadRoomImage(@PathVariable Long hotelId,
                                  @RequestParam("roomImage") CommonsMultipartFile roomImage,
                                  @SessionAttribute(name = "room") Room room, Model model) {



        if (roomImage.isEmpty()) {
            model.addAttribute("error", "Please select a Room Image.");
            model.addAttribute("hotelId", hotelId);

            return "room/roomImageUpload";
        }

        String extension = FilenameUtils.getExtension(roomImage.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            model.addAttribute("hotelId", hotelId);

            return "room/roomImageUpload";
        }

        try {
            byte[] roomImageData = IOUtils.toByteArray(roomImage.getInputStream());
            room.setRoomImage(roomImageData);

            hotelService.addRoom(hotelId, room);

            return "redirect:/hotel/" + hotelId;

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Room Image.");
            model.addAttribute("hotelId", hotelId);

            return "room/roomImageUpload";
        }
    }
}