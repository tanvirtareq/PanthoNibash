package net.therap.interceptor.authInterceptor;

import net.therap.model.Role;
import net.therap.model.SessionContext;
import net.therap.util.Util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class HotelAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext != null && Role.HOTEL.equals(sessionContext.getRole())
                && sessionContext.getId().equals(Util.getHotelIdFromRequest(request))) {

            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}
