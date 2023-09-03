package org.tanvir.patternLayout;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author tanvirtareq
 * @since 8/9/23
 */
public class CustomLogPatternLayout extends PatternLayout {

    @Override
    public String format(LoggingEvent event) {
        String message = super.format(event);

        String modifiedMessage = message.replaceAll("\\broomImage=\\[.*?\\]", "roomImage=[Excluded]")
                .replaceAll("\\bhotelImage=\\[.*?\\]", "hotelImage=[Excluded]")
                .replaceAll("\\bguestImage=\\[.*?\\]", "guestImage=[Excluded]")
                .replaceAll("\\bprofilePicture=\\[.*?\\]", "profilePicture=[Excluded]");

        return modifiedMessage;
    }
}
