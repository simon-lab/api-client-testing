package tests.requestResponseValidation.ReusableMethod;

public class separateHeaderBody {
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
}
