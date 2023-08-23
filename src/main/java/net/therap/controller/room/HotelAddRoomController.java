package net.therap.controller.room;

import net.therap.dto.ButtonDto;
import net.therap.dto.SuccessMessageDto;
import net.therap.helper.Helper;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private Helper helper;

    @GetMapping("/addRoom")
    public String addRoom(@PathVariable Long hotelId, Model model) {

        model.addAttribute("room", new Room());
        model.addAttribute("roomTypeOptions", roomTypeOptions);
        model.addAttribute("hotelId", hotelId);

        return "room/addRoom";
    }

    @PostMapping("/addRoom")
    public String processAddRoom(@PathVariable Long hotelId, @ModelAttribute @Valid Room room,
                                 BindingResult bindingResult, Model model, HttpServletRequest request) {

        roomNumberValidator.validateRoomNumbers(hotelId, room, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("room", room);
            model.addAttribute("roomTypeOptions", roomTypeOptions);
            model.addAttribute("hotelId", hotelId);

            return "room/addRoom";
        }

        helper.imageUploadHelper(model, request, "room.image.upload.page.heading",
                helper.roomImageUploadLink(hotelId));

        return "imageUpload";
    }

    @PostMapping("/addRoom/roomImageUpload")
    public String uploadRoomImage(@PathVariable Long hotelId,
                                  @RequestParam CommonsMultipartFile image,
                                  @SessionAttribute Room room, Model model, HttpServletRequest request) {

        if (image.isEmpty()) {
            model.addAttribute("error", "Please select a Room Image.");
            helper.imageUploadHelper(model, request, "room.image.upload.page.heading",
                    helper.roomImageUploadLink(hotelId));

            return "imageUpload";
        }

        String extension = FilenameUtils.getExtension(image.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            helper.imageUploadHelper(model, request, "room.image.upload.page.heading",
                    helper.roomImageUploadLink(hotelId));

            return "imageUpload";
        }

        try {
            byte[] roomImageData = IOUtils.toByteArray(image.getInputStream());
            room.setRoomImage(roomImageData);

            hotelService.addRoom(hotelId, room);

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + hotelId));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            SuccessMessageDto successMessageDto = new SuccessMessageDto("Successfully Room Added",
                    buttonDtoList);

            model.addAttribute("successMessageDto", successMessageDto);

            return "successMessage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Room Image.");
            helper.imageUploadHelper(model, request, "room.image.upload.page.heading",
                    helper.roomImageUploadLink(hotelId));

            return "imageUpload";
        }
    }
}