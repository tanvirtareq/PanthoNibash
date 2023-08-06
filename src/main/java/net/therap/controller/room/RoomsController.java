package net.therap.controller.room;

import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/25/23
 */
@Controller
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String showRooms(Model model, @RequestParam(value = "page", required = false) Long currentPage) {

        if(currentPage==null || currentPage<=0) {
            currentPage = new Long(1);
        }

        List<Room> rooms = roomService.getAll(currentPage);
        model.addAttribute("rooms", rooms);
        model.addAttribute("currentPage", currentPage);

        return "room/showRooms";
    }
}
