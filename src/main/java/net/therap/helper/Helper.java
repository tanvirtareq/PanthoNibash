package net.therap.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author tanvirtareq
 * @since 8/23/23
 */
@Component
public class Helper {
    @Autowired
    private MessageSource messageSource;

    public void imageUploadHelper(Model model, HttpServletRequest request, String headerCode, String postMappingLink) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale;

        if (localeResolver != null) {
            locale = localeResolver.resolveLocale(request);
        } else {
            locale = Locale.ENGLISH;
        }

        model.addAttribute("headerMessage", messageSource.getMessage(headerCode, null, locale));
        model.addAttribute("postMappingLink", postMappingLink);
    }

    public String guestImageUploadLink(Long roomId) {
        return "room/" + roomId +"/book/guestImageUpload";
    }

    public String roomImageUploadLink(Long hotelId) {
        return "hotel/"+hotelId + "/addRoom/roomImageUpload";
    }
}
