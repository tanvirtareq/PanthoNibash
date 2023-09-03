package org.tanvir.controller.room;

import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.helper.Helper;
import org.tanvir.model.Room;
import org.tanvir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */
@Controller
@RequestMapping("/room/{roomId}")
@SessionAttributes("room")
public class RoomEditController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Helper helper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        helper.addRoomHelper(room.getHotel().getId(), room, model, helper.roomEditLink(roomId));
        model.addAttribute("readOnly", false);

        return "room/roomPage";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long roomId, @ModelAttribute @Valid Room room,
                                  BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            helper.addRoomHelper(room.getHotel().getId(), room, model, helper.roomEditLink(roomId));
            model.addAttribute("readOnly", false);

            return "room/roomPage";
        }

        roomService.merge(room);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + room.getHotel().getId()));
        buttonDtoList.add(new ButtonDto("See room Details", "/room/" + room.getId()));
        buttonDtoList.add(new ButtonDto("Go to Home", "/"));

        DoneMessageDto doneMessageDto = new DoneMessageDto("Room Information Update Successful",
                buttonDtoList, helper.getMessageFromMessageCode("label.success", request));

        model.addAttribute("doneMessageDto", doneMessageDto);

        return "doneMessage";
    }
}