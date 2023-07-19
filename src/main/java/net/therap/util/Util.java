package net.therap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
public class Util {

    private static final String ROOM_NUMBER_PATTERN = "\\d+";
    public static List<String> formatStringToList(List<String> texts) {
        List<String> roomNumbers = new ArrayList<>();
        for(String text:texts) {
            Pattern pattern = Pattern.compile(ROOM_NUMBER_PATTERN);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                roomNumbers.add(matcher.group());
            }
        }

        return roomNumbers;
    }
}
