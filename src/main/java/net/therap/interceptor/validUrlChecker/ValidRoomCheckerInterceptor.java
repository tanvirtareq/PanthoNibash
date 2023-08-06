package net.therap.interceptor.validUrlChecker;

import net.therap.model.Room;
import net.therap.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

        final Map<String, String> pathVariables =
                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        return Long.parseLong(pathVariables.get("roomId"));
    }
}
