package net.therap.interceptor;

import net.therap.model.Booking;
import net.therap.model.Customer;
import net.therap.model.Hotel;
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
public class customerDetailsInterceptor implements HandlerInterceptor {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext == null) {
            response.sendRedirect("/search");

            return false;
        }

        Long customerId = getCustomerIdFromUrl(request);
        if(sessionContext.getRole().equals("HOTEL")) {
            return true;
        } else {
            if (customerId.equals(sessionContext.getId())) {
                return true;
            } else {
                response.sendRedirect("/search");
                return false;
            }
        }
    }

    private Long getCustomerIdFromUrl(HttpServletRequest request) {
        String[] pathSegments = request.getRequestURI().split("/");
        String customerId = pathSegments[2];

        return Long.parseLong(customerId);
    }

}
