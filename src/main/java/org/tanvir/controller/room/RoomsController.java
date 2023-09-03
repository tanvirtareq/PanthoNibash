package org.tanvir.controller.room;

import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.helper.Helper;
import org.tanvir.model.Room;
import org.tanvir.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/25/23
 */
@Controller
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Helper helper;

    @GetMapping("/rooms")
    public String showRooms(Model model, @RequestParam(value = "page", required = false) Long currentPage, HttpServletRequest request) {

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1L;
        }

        List<Room> rooms = roomService.getRoomList(currentPage);

        if (rooms.isEmpty()) {
            List<ButtonDto> buttonDtoList = helper.getCommunButtonList();
            DoneMessageDto doneMessageDto = new DoneMessageDto("Page not found", buttonDtoList,
                    helper.getMessageFromMessageCode("label.error", request));
            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("currentPage", currentPage);

        return "room/showRooms";
    }
}
