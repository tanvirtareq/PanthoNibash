package net.therap.controller.room;

import net.therap.model.Room;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Controller
@RequestMapping("/hotel/{id}")
@SessionAttributes({"room"})
public class HotelAddRoomController {

    @Autowired
    private HotelService hotelService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/addRoom")
    public String addRoom(@PathVariable Long id, Model model) {

        model.addAttribute("room", new Room());
        model.addAttribute("roomTypeOptions", roomTypeOptions);
        model.addAttribute("hotelId", id);

        return "room/addRoom";
    }

    @PostMapping("/addRoom")
    public String processAddRoom(@PathVariable Long id, @ModelAttribute @Valid Room room,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("roomTypeOptions", roomTypeOptions);
            model.addAttribute("hotelId", id);

            return "room/addRoom";
        }

        model.addAttribute("hotelId", id);

        return "room/roomImageUpload";
    }

    @PostMapping("/addroom/roomImageUpload")
    public String uploadRoomImage(@PathVariable Long id,
                                  @RequestParam("roomImage") CommonsMultipartFile roomImage,
                                  @SessionAttribute(name = "room") Room room, Model model) {



        if (roomImage.isEmpty()) {
            model.addAttribute("error", "Please select a Room Image.");
            model.addAttribute("hotelId", id);

            return "room/roomImageUpload";
        }

        String extension = FilenameUtils.getExtension(roomImage.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            model.addAttribute("hotelId", id);

            return "room/roomImageUpload";
        }

        try {
            byte[] roomImageData = IOUtils.toByteArray(roomImage.getInputStream());
            room.setRoomImage(roomImageData);

            hotelService.addRoom(id, room);

            return "redirect:/hotel/" + id;

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Room Image.");
            model.addAttribute("hotelId", id);

            return "room/roomImageUpload";
        }
    }
}