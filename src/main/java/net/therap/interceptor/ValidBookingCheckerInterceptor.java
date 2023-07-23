package net.therap.interceptor;

import net.therap.model.Booking;
import net.therap.service.BookingService;
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
public class ValidBookingCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private BookingService bookingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long bookingId = getBookingIdFromUrl(request);

        Booking book = bookingService.findById(bookingId);

        if (book != null) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }

    private Long getBookingIdFromUrl(HttpServletRequest request) {

        String[] pathSegments = request.getRequestURI().split("/");
        String bookingId = pathSegments[2];

        return Long.parseLong(bookingId);
    }

}
