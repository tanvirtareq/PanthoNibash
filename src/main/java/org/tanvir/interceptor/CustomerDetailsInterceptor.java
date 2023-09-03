package org.tanvir.interceptor;

import org.tanvir.model.Role;
import org.tanvir.model.SessionContext;
import org.tanvir.util.Util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class CustomerDetailsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext == null) {
            response.sendRedirect("/search");

            return false;
        }

        if (sessionContext.getRole().equals(Role.HOTEL) || Util.getCustomerIdFromRequest(request).equals(sessionContext.getId())) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}
