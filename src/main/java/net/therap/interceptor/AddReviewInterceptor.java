package net.therap.interceptor;

import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.model.SessionContext;
import net.therap.service.BookingService;
import net.therap.service.CustomerService;
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
public class AddReviewInterceptor implements HandlerInterceptor {

    @Autowired
    private BookingService bookingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long customerId = getCustomerIdFromUrl(request);
        Long bookingId = getBookingIdFromUrl(request);

        Booking booking = bookingService.findById(bookingId);

        if(booking.getCustomer()!=null && Objects.equals(booking.getCustomer().getId(), customerId)) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }

    private Long getBookingIdFromUrl(HttpServletRequest request) {

        String[] pathSegments = request.getRequestURI().split("/");
        String customerId = pathSegments[4];
        System.out.println(customerId);

        return Long.parseLong(customerId);
    }

    private Long getCustomerIdFromUrl(HttpServletRequest request) {

        String[] pathSegments = request.getRequestURI().split("/");
        String customerId = pathSegments[2];
        System.out.println(customerId);

        return Long.parseLong(customerId);
    }
}
