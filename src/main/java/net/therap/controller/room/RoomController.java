package net.therap.controller.room;

import net.therap.helper.Helper;
import net.therap.model.Room;
import net.therap.model.SessionContext;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Controller
@RequestMapping("/room")
@SessionAttributes("sessionContext")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Helper helper;

    @GetMapping("/{roomId}")
    public String showRoom(@PathVariable Long roomId, Model model,
                           @SessionAttribute(required = false)SessionContext sessionContext) {

        Room room = roomService.findById(roomId);
        helper.addRoomHelper(room.getHotel().getId(), room, model, "#");
        model.addAttribute("readOnly", true);
        model.addAttribute("hasEditAccess", helper.hasRoomEditAccess(room.getHotel().getId(), sessionContext));

        return "room/roomPage";
    }
}