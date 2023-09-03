package org.tanvir.controller.booking;

import org.tanvir.constants.UrlConstants;
import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.helper.Helper;
import org.tanvir.model.Booking;
import org.tanvir.model.Room;
import org.tanvir.model.SessionContext;
import org.tanvir.service.BookingService;
import org.tanvir.service.RoomService;
import org.tanvir.util.Util;
import org.tanvir.validator.BookingValidator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Controller
@RequestMapping("/room")
@SessionAttributes("booking")
public class RoomBookController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingValidator bookingValidator;

    @Autowired
    private Helper helper;

    @InitBinder("booking")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(bookingValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/{roomId}/book")
    public String showRoomBookPage(@PathVariable Long roomId, Model model, HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        if (sessionContext == null) {
            return "redirect:customer/login";
        }

        Booking booking = helper.createBookingFromSessionContext(sessionContext);
        Room room = roomService.findById(roomId);
        model.addAttribute("booking", booking);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomPrice", room.getPrice());

        return "booking/roomBookPage";
    }

    @PostMapping("/{roomId}/book")
    public String processRoomBook(@PathVariable Long roomId, @ModelAttribute("booking") @Valid Booking booking,
                                  BindingResult bindingResult, Model model, HttpSession httpSession,
                                  HttpServletRequest request) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (sessionContext == null) {
            return "redirect:customer/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roomId", roomId);

            return "booking/roomBookPage";
        }

        Room room = roomService.findById(roomId);

        if (!roomService.availableRoom(room, booking.getCheckInDate(), booking.getCheckOutDate())) {

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("Find available room for this date", "/search?checkIn="
                    + booking.getCheckInDate().toString() + "&checkOut=" + booking.getCheckOutDate()));

            DoneMessageDto doneMessageDto = new DoneMessageDto("Can not book. Room is not available for this date",
                    buttonDtoList, helper.getMessageFromMessageCode("label.error", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";
        }

        booking.setRoomNumber(roomService.getRoomNumber(room, booking.getCheckInDate(), booking.getCheckOutDate()));
        booking.setRoom(room);

        helper.imageUploadHelper(model, request, "guest.photo.upload.heading", UrlConstants.GUEST_IMAGE_UPLOAD_URL);

        return "imageUpload";
    }

    @PostMapping("/book/guestImageUpload")
    public String uploadProfilePicture(@RequestParam CommonsMultipartFile image,
                                       @SessionAttribute Booking booking, Model model,
                                       HttpServletRequest request) {

        if (image.isEmpty()) {
            model.addAttribute("error", "Please select an Image.");
            helper.imageUploadHelper(model, request, "guest.photo.upload.heading", UrlConstants.GUEST_IMAGE_UPLOAD_URL);

            return "imageUpload";
        }

        String extension = FilenameUtils.getExtension(image.getOriginalFilename());

        if (!Util.allowedImageExtension(extension, model)) {
            model.addAttribute("error", "Please select an Image.");
            helper.imageUploadHelper(model, request, "guest.photo.upload.heading", UrlConstants.GUEST_IMAGE_UPLOAD_URL);

            return "imageUpload";
        }

        try {
            byte[] guestImageData = IOUtils.toByteArray(image.getInputStream());
            booking.setGuestImage(guestImageData);
            bookingService.save(booking);

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Booking Details", "/booking/" + booking.getId()));
            buttonDtoList.add(new ButtonDto("Go to Home", "/"));

            DoneMessageDto doneMessageDto = new DoneMessageDto("Successfully Booked", buttonDtoList,
                    helper.getMessageFromMessageCode("label.success", request));

            model.addAttribute("doneMessageDto", doneMessageDto);

            return "doneMessage";

        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload the Image.");
            helper.imageUploadHelper(model, request, "guest.photo.upload.heading", UrlConstants.GUEST_IMAGE_UPLOAD_URL);

            return "imageUpload";
        }
    }
}