package net.therap.controller.room;

import net.therap.dto.ButtonDto;
import net.therap.dto.DoneMessageDto;
import net.therap.helper.Helper;
import net.therap.model.Room;
import net.therap.service.HotelService;
import net.therap.util.Util;
import net.therap.validator.RoomNumberValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoomNumberValidator roomNumberValidator;

    @Autowired
    private Helper helper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/addRoom")
    public String addRoom(@PathVariable Long hotelId, Model model) {
        helper.addRoomHelper(hotelId, new Room(), model, helper.addRoomLink(hotelId));
        model.addAttribute("readOnly", false);

        return "room/roomPage";
    }

    @PostMapping("/addRoom")
    public String processAddRoom(@PathVariable Long hotelId, @ModelAttribute @Valid Room room,
                                 BindingResult bindingResult, Model model, HttpServletRequest request) {

        roomNumberValidator.validateRoomNumbers(hotelId, room, bindingResult);

        if (bindingResult.hasErrors()) {
            helper.addRoomHelper(hotelId, room, model, helper.addRoomLink(hotelId));
            model.addAttribute("readOnly", false);

            return "room/roomPage";
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

            DoneMessageDto doneMessageDto = new DoneMessageDto("Successfully Room Added",
                    buttonDtoList, helper.getMessageFromMessageCode("label.success", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Room Image.");
            helper.imageUploadHelper(model, request, "room.image.upload.page.heading",
                    helper.roomImageUploadLink(hotelId));

            return "imageUpload";
        }
    }
}