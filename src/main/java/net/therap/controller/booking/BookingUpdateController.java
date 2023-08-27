package net.therap.controller.booking;

import net.therap.dto.ButtonDto;
import net.therap.dto.DoneMessageDto;
import net.therap.helper.Helper;
import net.therap.model.Booking;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.HotelService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 8/16/23
 */
@Controller
@RequestMapping("/hotel/{hotelId}/booking/{bookingId}")
public class BookingUpdateController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private Helper helper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/cancel")
    public String bookingCancel(@PathVariable Long bookingId, @PathVariable Long hotelId, Model model,
                                HttpSession httpSession, HttpServletRequest request) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        String bookingUpdateErrorMessage = helper.bookingUpdateErrorMessage(model, sessionContext, request, hotelId, bookingId);

        if (bookingUpdateErrorMessage != null) {
            return bookingUpdateErrorMessage;
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canCancelBooking(booking, sessionContext)) {
            return helper.canNotCancelErrorMessage(model, hotelId, bookingId, request);
        }

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        helper.commonButtonForHotel(buttonDtoList, hotelId);
        buttonDtoList.add(new ButtonDto("Go to room page", Util.generateRoomUrl(booking.getRoom().getId())));
        buttonDtoList.add(new ButtonDto("Book again for this room",
                Util.generateRoomBookUrl(booking.getRoom().getId())));

        DoneMessageDto doneMessageDto = new DoneMessageDto("Successfully booking cancelled"
                , buttonDtoList, helper.getMessageFromMessageCode("label.success", request));

        bookingService.delete(booking);

        model.addAttribute("doneMessageDto", doneMessageDto);

        return "doneMessage";
    }


    @GetMapping("/update-booking-checkout-date")
    public String bookingCheckoutDateUpdateForm(@PathVariable Long bookingId, @PathVariable Long hotelId, Model model,
                                                HttpSession httpSession, HttpServletRequest request) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        String bookingUpdateErrorMessage = helper.bookingUpdateErrorMessage(model, sessionContext, request, hotelId, bookingId);

        if (bookingUpdateErrorMessage != null) {
            return bookingUpdateErrorMessage;
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            return helper.canNotUpdateErrorMessage(model, hotelId, bookingId, request);
        }

        model.addAttribute("booking", booking);
        model.addAttribute("formPostUrl", Util.generateUpdateBookingCheckoutDate(booking));

        return "booking/updateBookingCheckoutDateForm";
    }

    @PostMapping("/update-booking-checkout-date")
    public String bookingCheckoutDateUpdateFormProcess(@PathVariable Long bookingId, @PathVariable Long hotelId,
                                                       Model model, HttpSession httpSession,
                                                       @ModelAttribute("booking") Booking bookingNew,
                                                       HttpServletRequest request) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");
        String bookingUpdateErrorMessage = helper.bookingUpdateErrorMessage(model, sessionContext, request, hotelId, bookingId);

        if (bookingUpdateErrorMessage != null) {
            return bookingUpdateErrorMessage;
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            return helper.canNotUpdateErrorMessage(model, hotelId, bookingId, request);
        }

        if (!helper.validUpdatedCheckoutDate(bookingNew, booking)) {

            String errorMessage = "Updated checkout date should be before " + booking.getCheckOutDate() +
                    " and after " + booking.getCheckInDate();

            model.addAttribute("booking", booking);
            model.addAttribute("doneMessage", errorMessage);
            model.addAttribute("formPostUrl", Util.generateUpdateBookingCheckoutDate(booking));

            return "booking/updateBookingCheckoutDateForm";
        }

        booking.setCheckOutDate(bookingNew.getCheckOutDate());
        bookingService.update(booking);

        return helper.bookingUpdateSuccessMessage(model, hotelId, bookingId, request);
    }
}
