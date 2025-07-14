package tests.requestResponseValidation.ReusableMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class separateCell {
    public static String header(String cellContent) {
        String[] parts = cellContent.split("}\n");
        String header = null;

        if (parts.length >= 2) {
            header = parts[0].trim() + "\n}";

        } else {
            System.out.println("Format tidak sesuai, tidak ada pemisahan yang jelas.");
        }

        return header;
    }

    public static String body(String cellContent) {
        String[] parts = cellContent.split("}\n");
        String body = null;

        if (parts.length >= 2) {
            body = parts[1].trim();

        } else {
            System.out.println("Format tidak sesuai, tidak ada pemisahan yang jelas.");
        }

        return body;
    }

    public static String extractExpectedRC(String input) {
        String rcRegex = "Error Code:\\s*([\\w]+)";
        Pattern pattern = Pattern.compile(rcRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static String extractExpectedRM(String input) {
        String msgRegex = "Error Message:\\s*\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(msgRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}