package net.therap.util;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (String text : texts) {
            Pattern pattern = Pattern.compile(ROOM_NUMBER_PATTERN);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                roomNumbers.add(matcher.group());
            }
        }

        return roomNumbers;
    }

    public static boolean allowedImageExtension(String extension, Model model) {
        String[] allowedExtensions = {"jpg", "jpeg", "png", "webp"};

        if (!Arrays.asList(allowedExtensions).contains(extension)) {
            model.addAttribute("error", "Only JPG, JPEG, WEBP and PNG files are allowed.");

            return false;
        }
        return true;
    }

    public static boolean partialMatch(String name, String patternName) {
        Pattern pattern = Pattern.compile(patternName, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }
}
