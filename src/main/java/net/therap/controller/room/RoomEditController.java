package net.therap.controller.room;

import net.therap.dto.ButtonDto;
import net.therap.dto.SuccessMessageDto;
import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 7/23/23
 */
@Controller
@RequestMapping("/room/{roomId}")
public class RoomEditController {

    @Autowired
    private RoomService roomService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    @GetMapping("/edit")
    public String showEditForm(@PathVariable Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("roomTypeOptions", roomTypeOptions);

        return "room/showRoomEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long roomId, @ModelAttribute("room") @Valid Room room,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roomTypeOptions", roomTypeOptions);

            return "room/showRoomEditForm";
        }

        roomService.update(roomId, room.getType(), room.getPrice(), room.getNumberOfBed());
        room = roomService.findById(roomId);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Hotel Details", "/hotel/" + room.getHotel().getId()));
        buttonDtoList.add(new ButtonDto("See room Details", "/room/" + room.getId()));
        buttonDtoList.add(new ButtonDto("Go to Home", "/"));

        SuccessMessageDto successMessageDto = new SuccessMessageDto("Room Information Update Successful",
                buttonDtoList);

        model.addAttribute("successMessageDto", successMessageDto);

        return "successMessage";
    }
}