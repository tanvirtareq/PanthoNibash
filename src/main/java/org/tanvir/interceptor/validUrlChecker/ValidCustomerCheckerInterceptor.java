package org.tanvir.interceptor.validUrlChecker;

import org.tanvir.model.Customer;
import org.tanvir.service.CustomerService;
import org.tanvir.util.Util;
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
public class ValidCustomerCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long customerId = Util.getCustomerIdFromRequest(request);
        Customer customer = customerService.findById(customerId);

        if (customer != null) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}