package org.tanvir.helper;

import org.tanvir.constants.UrlConstants;
import org.tanvir.constants.ViewConstants;
import org.tanvir.dto.ButtonDto;
import org.tanvir.dto.DoneMessageDto;
import org.tanvir.model.*;
import org.tanvir.model.*;
import org.tanvir.service.CustomerService;
import org.tanvir.service.HotelService;
import org.tanvir.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author tanvirtareq
 * @since 8/23/23
 */
@Component
public class Helper {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CustomerService customerService;

    @Value("#{roomTypeOptions}")
    private Map<String, String> roomTypeOptions;

    public void imageUploadHelper(Model model, HttpServletRequest request, String headerCode, String postMappingLink) {
        model.addAttribute("headerMessage", getMessageFromMessageCode(headerCode, request));
        model.addAttribute("postMappingLink", postMappingLink);
    }

    public String roomImageUploadLink(Long hotelId) {
        return "hotel/" + hotelId + "/addRoom/roomImageUpload";
    }

    public Object isHotelLoggedIn(Hotel hotel, SessionContext sessionContext) {

        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole())
                && hotel.getId().equals(sessionContext.getId());
    }

    public void addRoomHelper(Long hotelId, Room room, Model model, String link) {
        model.addAttribute("room", room);
        model.addAttribute("roomTypeOptions", roomTypeOptions);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("hotel", hotelService.findById(hotelId));
        model.addAttribute("link", link);
    }

    public String addRoomLink(Long hotelId) {
        return "hotel/" + hotelId + "/addRoom";
    }

    public String roomEditLink(Long roomId) {
        return "room/" + roomId + "/edit";
    }

    public Booking createBookingFromSessionContext(SessionContext sessionContext) {

        if (sessionContext.getRole().equals(Role.CUSTOMER)) {
            return new Booking(customerService.findById(sessionContext.getId()));
        }

        return new Booking();
    }

    public List<ButtonDto> getCommunButtonList() {
        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("Go to Home", "/"));
        buttonDtoList.add(new ButtonDto("Search", "/search"));
        buttonDtoList.add(new ButtonDto("Hotel List", "/hotels"));
        buttonDtoList.add(new ButtonDto("Room List", "/rooms"));

        return buttonDtoList;
    }

    public boolean customerLoggedIn(Long customerId, SessionContext sessionContext) {
        return sessionContext != null && Role.CUSTOMER.equals(sessionContext.getRole()) && customerId.equals(sessionContext.getId());
    }

    public String getMessageFromMessageCode(String code, HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale;

        if (localeResolver != null) {
            locale = localeResolver.resolveLocale(request);
        } else {
            locale = Locale.ENGLISH;
        }
        return messageSource.getMessage(code, null, locale);
    }

    public String bookingUpdateErrorMessage(Model model, SessionContext sessionContext, HttpServletRequest request,
                                            Long hotelId, Long bookingId) {

        if (!loggedInAsHotel(sessionContext)) {
            return unauthorizedErrorMessage(model, sessionContext, request);
        }

        if (hotelService.findById(hotelId) == null) {
            return hotelNotFoundErrorMessage(model, request);
        }

        if (!hotelService.hasBookingManipulationAuthorization(hotelId, bookingId, sessionContext)) {
            return bookingNotFoundErrorMessage(model, request, sessionContext);
        }

        return null;
    }

    public String bookingUpdateSuccessMessage(Model model, Long hotelId, Long bookingId, HttpServletRequest request) {
        List<ButtonDto> buttonDtoList = new ArrayList<>();
        commonButtonForBooking(bookingId, buttonDtoList);
        commonButtonForHotel(buttonDtoList, hotelId);
        DoneMessageDto doneMessageDto = new DoneMessageDto("Checkout Date updated successfully", buttonDtoList,
                getMessageFromMessageCode("label.success", request));
        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    public void commonButtonForBooking(Long bookingId, List<ButtonDto> buttonDtoList) {
        buttonDtoList.add(new ButtonDto("Booking details", Util.generateBookingDetailsUrl(bookingId)));
    }

    public boolean validUpdatedCheckoutDate(Booking bookingNew, Booking booking) {
        return bookingNew.getCheckOutDate().isBefore(booking.getCheckOutDate())
                && bookingNew.getCheckOutDate().isAfter(booking.getCheckInDate());
    }

    public String canNotUpdateErrorMessage(Model model, Long hotelId, Long bookingId, HttpServletRequest request) {
        List<ButtonDto> buttonDtoList = new ArrayList<>();
        commonButtonForBooking(bookingId, buttonDtoList);
        commonButtonForHotel(buttonDtoList, hotelId);
        DoneMessageDto doneMessageDto = new DoneMessageDto("Can not update booking checkout date",
                buttonDtoList, getMessageFromMessageCode("label.error", request));

        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    public String canNotCancelErrorMessage(Model model, Long hotelId, Long bookingId, HttpServletRequest request) {
        List<ButtonDto> buttonDtoList = new ArrayList<>();
        commonButtonForBooking(bookingId, buttonDtoList);
        commonButtonForHotel(buttonDtoList, hotelId);
        DoneMessageDto doneMessageDto = new DoneMessageDto("Can not cancel this booking", buttonDtoList,
                getMessageFromMessageCode("label.error", request));
        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    public String bookingNotFoundErrorMessage(Model model, HttpServletRequest request, SessionContext sessionContext) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();

        if (sessionContext != null && Role.HOTEL.equals(sessionContext.getRole())) {
            commonButtonForHotel(buttonDtoList, sessionContext.getId());
        }

        if (sessionContext != null && Role.CUSTOMER.equals(sessionContext.getRole())) {
            commonButtonForCustomer(buttonDtoList, sessionContext.getId());
        }

        DoneMessageDto doneMessageDto = new DoneMessageDto("Booking not found", buttonDtoList,
                getMessageFromMessageCode("label.error", request));
        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    private void commonButtonForCustomer(List<ButtonDto> buttonDtoList, Long customerId) {
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateCustomerBookingListUrl(customerId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateCustomerProfileUrl(customerId)));
    }

    public String hotelNotFoundErrorMessage(Model model, HttpServletRequest request) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See available hotel list", UrlConstants.HOTELS_URL));
        DoneMessageDto doneMessageDto = new DoneMessageDto("Hotel not found", buttonDtoList,
                getMessageFromMessageCode("label.error", request));
        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    public String unauthorizedErrorMessage(Model model, SessionContext sessionContext, HttpServletRequest request) {

        List<ButtonDto> buttonDtoList = new ArrayList<>();
        buttonDtoList.add(new ButtonDto("See available hotel list", UrlConstants.HOTELS_URL));
        if (sessionContext == null) {
            buttonDtoList.add(new ButtonDto("Login as hotel", UrlConstants.HOTEL_LOGIN_URL));
            buttonDtoList.add(new ButtonDto("Signup as hotel", UrlConstants.HOTEL_SIGNUP_URL));
        }

        DoneMessageDto doneMessageDto = new DoneMessageDto("You are not logged in as hotel", buttonDtoList,
                getMessageFromMessageCode("label.error", request));
        model.addAttribute("doneMessageDto", doneMessageDto);

        return ViewConstants.DONE_PAGE;
    }

    public boolean loggedInAsHotel(SessionContext sessionContext) {
        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole());
    }

    public void commonButtonForHotel(List<ButtonDto> buttonDtoList, Long hotelId) {
        buttonDtoList.add(new ButtonDto("See Booking list", Util.generateBookingListUrl(hotelId)));
        buttonDtoList.add(new ButtonDto("Go to profile", Util.generateHotelProfileUrl(hotelId)));
    }

    public boolean hasRoomEditAccess(Long hotelId, SessionContext sessionContext) {
        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole()) && sessionContext.getId().equals(hotelId);
    }
}
