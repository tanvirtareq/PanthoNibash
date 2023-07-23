package net.therap.interceptor;

import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.model.Hotel;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class BookingDetailsInterceptor implements HandlerInterceptor {

    @Autowired
    private BookingService bookingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext == null) {
            response.sendRedirect("/search");
            return false;
        }

        Long bookingId = getBookingIdFromUrl(request);
        Booking booking = bookingService.findById(bookingId);

        if (sessionContext.getRole().equals("HOTEL")) {

            if (hotelAuth((Hotel) sessionContext.getUser(), booking)) {
                return true;
            }

        } else {

            if (customerAuth((Customer) sessionContext.getUser(), booking)) {
                return true;
            }
        }
        response.sendRedirect("/search");

        return false;
    }

    private boolean customerAuth(Customer user, Booking booking) {
        return Objects.equals(booking.getCustomer().getId(), user.getId());
    }

    private boolean hotelAuth(Hotel user, Booking booking) {
        return Objects.equals(booking.getRoom().getHotel().getId(), user.getId());
    }

    private Long getBookingIdFromUrl(HttpServletRequest request) {

        String[] pathSegments = request.getRequestURI().split("/");
        String customerId = pathSegments[2];
        System.out.println(customerId);

        return Long.parseLong(customerId);
    }
}