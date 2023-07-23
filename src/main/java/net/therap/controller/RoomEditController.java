package net.therap.controller;

import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return "showRoomEditForm";
    }

    @PostMapping("/edit")
    public String processEditForm(@PathVariable Long roomId, @ModelAttribute("room") @Valid Room room,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roomTypeOptions", roomTypeOptions);

            return "showRoomEditForm";
        }

        roomService.update(roomId, room.getType(), room.getPrice(), room.getNumberOfBed());

        return "redirect:/room/" + roomId;
    }
}