package net.therap.interceptor;

import net.therap.model.Booking;
import net.therap.service.BookingService;
import net.therap.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class AddReviewInterceptor implements HandlerInterceptor {

    @Autowired
    private BookingService bookingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long bookingId = Util.getBookingIdFromRequest(request);
        Booking booking = bookingService.findById(bookingId);

        if (booking.getCustomer() != null) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}
