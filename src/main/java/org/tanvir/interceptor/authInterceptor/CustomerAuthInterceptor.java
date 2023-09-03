package org.tanvir.interceptor.authInterceptor;

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
public class CustomerAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext != null && Role.CUSTOMER.equals(sessionContext.getRole())
                && sessionContext.getId().equals(Util.getCustomerIdFromRequest(request))) {

            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}
