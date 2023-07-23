package net.therap.interceptor;

import net.therap.model.SessionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanvirtareq
 * @since 7/24/23
 */
@Component
public class NonUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        SessionContext sessionContext = (SessionContext) request.getSession().getAttribute("sessionContext");

        if (sessionContext == null) {
            return true;
        }
        response.sendRedirect("/search");

        return false;
    }
}
