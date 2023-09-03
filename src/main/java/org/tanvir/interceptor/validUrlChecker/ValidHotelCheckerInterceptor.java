package org.tanvir.interceptor.validUrlChecker;

import org.tanvir.model.Hotel;
import org.tanvir.service.HotelService;
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
public class ValidHotelCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private HotelService hotelService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long hotelId = Util.getHotelIdFromRequest(request);
        Hotel hotel = hotelService.findById(hotelId);

        if (hotel != null) {
            return true;
        }

        response.sendRedirect("/search");

        return false;
    }
}
