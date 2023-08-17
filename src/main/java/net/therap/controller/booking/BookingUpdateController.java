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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cancel")
    public String bookingCancel(@PathVariable Long bookingId, @PathVariable Long hotelId, Model model,
                                HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (!loggedInAsHotel(sessionContext)) {
            return unauthorizedErrorMessage(model, sessionContext);
        }

        if (hotelService.findById(hotelId) == null) {
            return hotelNotFoundErrorMessage(model);
        }

        if (!hotelService.hasBookingManipulationAuthorization(hotelId, bookingId, sessionContext)) {
            return bookingNotFoundErrorMessage(model, hotelId);
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canCancelBooking(booking, sessionContext)) {
            return canNotCancelErrorMessage(model, hotelId, bookingId);
        }

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to room page", Util.generateRoomUrl(booking.getRoom().getId())));
        buttonDtoList.add(new ButtonDto("Book again for this room",
                Util.generateRoomBookUrl(booking.getRoom().getId())));

        SuccessMessageDto successMessageDto = new SuccessMessageDto("Successfully booking cancelled"
                , buttonDtoList);

        bookingService.delete(booking);

        model.addAttribute("successMessageDto", successMessageDto);

        return "successMessage";
    }


    @GetMapping("/update-booking-checkout-date")
    public String bookingCheckoutDateUpdateForm(@PathVariable Long bookingId, @PathVariable Long hotelId, Model model,
                                                HttpSession httpSession) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (!loggedInAsHotel(sessionContext)) {
            return unauthorizedErrorMessage(model, sessionContext);
        }

        if (hotelService.findById(hotelId) == null) {
            return hotelNotFoundErrorMessage(model);
        }

        if (!hotelService.hasBookingManipulationAuthorization(hotelId, bookingId, sessionContext)) {
            return bookingNotFoundErrorMessage(model, hotelId);
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            return canNotUpdateErrorMessage(model, hotelId, bookingId);
        }

        model.addAttribute("booking", booking);
        model.addAttribute("formPostUrl", Util.generateUpdateBookingCheckoutDate(booking));

        return "booking/updateBookingCheckoutDateForm";
    }

    @PostMapping("/update-booking-checkout-date")
    public String bookingCheckoutDateUpdateFormProcess(@PathVariable Long bookingId, @PathVariable Long hotelId,
                                                       Model model, HttpSession httpSession,
                                                       @ModelAttribute("booking") Booking bookingNew) {

        SessionContext sessionContext = (SessionContext) httpSession.getAttribute("sessionContext");

        if (!loggedInAsHotel(sessionContext)) {
            return unauthorizedErrorMessage(model, sessionContext);
        }

        if (hotelService.findById(hotelId) == null) {
            return hotelNotFoundErrorMessage(model);
        }

        if (!hotelService.hasBookingManipulationAuthorization(hotelId, bookingId, sessionContext)) {
            return bookingNotFoundErrorMessage(model, hotelId);
        }

        Booking booking = bookingService.findById(bookingId);

        if (!Util.canUpdateBookingCheckoutDate(booking, sessionContext)) {
            return canNotUpdateErrorMessage(model, hotelId, bookingId);
        }

        if (!validUpdatedCheckoutDate(bookingNew, booking)) {

            String errorMessage = "Updated checkout date should be before " + booking.getCheckOutDate() +
                    " and after " + booking.getCheckInDate();

            model.addAttribute("booking", booking);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("formPostUrl", Util.generateUpdateBookingCheckoutDate(booking));

            return "booking/updateBookingCheckoutDateForm";
        }

        booking.setCheckOutDate(bookingNew.getCheckOutDate());

        bookingService.update(booking);

        return bookingUpdateSuccessMessage(model, hotelId, bookingId);
    }

    private String bookingUpdateSuccessMessage(Model model, Long hotelId, Long bookingId) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Booking details", Util.generateBookingDetailsUrl(bookingId)));
        SuccessMessageDto successMessageDto = new SuccessMessageDto("Checkout Date updated successfully", buttonDtoList);
        model.addAttribute("successMessageDto", successMessageDto);

        return "successMessage";
    }

    private boolean validUpdatedCheckoutDate(Booking bookingNew, Booking booking) {
        return bookingNew.getCheckOutDate().isBefore(booking.getCheckOutDate())
                && bookingNew.getCheckOutDate().isAfter(booking.getCheckInDate());
    }

    private String canNotUpdateErrorMessage(Model model, Long hotelId, Long bookingId) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Booking details", Util.generateBookingDetailsUrl(bookingId)));
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("Can not update booking checkout date",
                buttonDtoList);

        model.addAttribute("errorMessageDto", errorMessageDto);

        return "errorMessage";
    }

    private String canNotCancelErrorMessage(Model model, Long hotelId, Long bookingId) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Booking details", Util.generateBookingDetailsUrl(bookingId)));
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("Can not cancel this booking", buttonDtoList);
        model.addAttribute("errorMessageDto", errorMessageDto);

        return "errorMessage";
    }

    private String bookingNotFoundErrorMessage(Model model, Long hotelId) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("Booking not found", buttonDtoList);
        model.addAttribute("errorMessageDto", errorMessageDto);

        return "errorMessage";
    }

    private String hotelNotFoundErrorMessage(Model model) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See available hotel list", UrlConstants.HOTELS_URL));
        ErrorMessageDto errorMessageDto = new ErrorMessageDto("Hotel not found", buttonDtoList);
        model.addAttribute("errorMessageDto", errorMessageDto);

        return "errorMessage";
    }

    private String unauthorizedErrorMessage(Model model, SessionContext sessionContext) {

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

    private boolean loggedInAsHotel(SessionContext sessionContext) {
        return sessionContext != null && "HOTEL".equals(sessionContext.getRole());
    }
}
