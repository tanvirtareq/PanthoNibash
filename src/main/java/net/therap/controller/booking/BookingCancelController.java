package net.therap.controller.booking;

import net.therap.constants.UrlConstants;
import net.therap.dto.ButtonDto;
import net.therap.dto.ErrorMessageDto;
import net.therap.dto.SuccessMessageDto;
import net.therap.model.Booking;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.HotelService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 8/16/23
 */
@Controller
@RequestMapping("/hotel/{hotelId}/booking/{bookingId}/cancel")
public class BookingCancelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String showBookingCancelForm(@PathVariable Long bookingId, @PathVariable Long hotelId, Model model,
                                        HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (sessionContext == null || "CUSTOMER".equals(sessionContext.getRole())) {

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See available hotel list", UrlConstants.HOTELS_URL));
            if (sessionContext == null) {
                buttonDtoList.add(new ButtonDto("Login as hotel", UrlConstants.HOTEL_LOGIN_URL));
                buttonDtoList.add(new ButtonDto("Signup as hotel", UrlConstants.HOTEL_SIGNUP_URL));
            }

            ErrorMessageDto errorMessageDto = new ErrorMessageDto("You are not logged in as hotel", buttonDtoList);
            model.addAttribute("errorMessageDto", errorMessageDto);

            return "errorMessage";
        }

        if (hotelService.findById(hotelId) == null) {
            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See available hotel list", UrlConstants.HOTELS_URL));
            ErrorMessageDto errorMessageDto = new ErrorMessageDto("Hotel not found", buttonDtoList);
            model.addAttribute("errorMessageDto", errorMessageDto);

            return "errorMessage";
        }

        if (!hotelService.hasBookingManipulationAuthorization(hotelId, bookingId, sessionContext)) {

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
            buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
            ErrorMessageDto errorMessageDto = new ErrorMessageDto("Booking not found", buttonDtoList);
            model.addAttribute("errorMessageDto", errorMessageDto);

            return "errorMessage";
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canCancelBooking(booking, sessionContext)) {

            List<ButtonDto> buttonDtoList = new ArrayList<>();
            buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
            buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
            buttonDtoList.add(new ButtonDto("Booking details", Util.generateBookingDetailsUrl(bookingId)));
            ErrorMessageDto errorMessageDto = new ErrorMessageDto("Can not cancel this booking", buttonDtoList);
            model.addAttribute("errorMessageDto", errorMessageDto);

            return "errorMessage";
        }

        bookingService.delete(booking);

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to room page", Util.generateRoomUrl(booking.getRoom().getId())));
        buttonDtoList.add(new ButtonDto("Book again for this room",
                Util.generateRoomBookUrl(booking.getRoom().getId())));

        SuccessMessageDto successMessageDto = new SuccessMessageDto("Successfully booking cancelled"
                , buttonDtoList);

        model.addAttribute("successMessageDto", successMessageDto);

        return "successMessage";
    }

}
