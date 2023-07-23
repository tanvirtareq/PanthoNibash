package net.therap.controller;

import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public String showRoom(@PathVariable Long id, Model model) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);

        return "roomPage";
    }
}