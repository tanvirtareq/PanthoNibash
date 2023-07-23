package net.therap.interceptor;

import net.therap.model.Room;
import net.therap.service.RoomService;
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
public class ValidRoomCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private RoomService roomService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long roomId = getRoomIdFromUrl(request);

        Room room = roomService.findById(roomId);

        if (room != null) {
            return true;
        }

        response.sendRedirect("/search");
        return false;
    }

    private Long getRoomIdFromUrl(HttpServletRequest request) {

        String[] pathSegments = request.getRequestURI().split("/");
        String roomId = pathSegments[2];
        
        return Long.parseLong(roomId);
    }
}
