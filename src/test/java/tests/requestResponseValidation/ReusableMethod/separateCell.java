package tests.requestResponseValidation.ReusableMethod;

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

    public static String[] extractExpectedRC(String input) {
        String responseCodeRegex = "Response Code:\\s*(\\S+)";
        String responseMessageRegex = "Response Message:\\s*\"([^\"]+)\"";

        String responseCode = "";
        String responseMessage = "";

        // Mencari Error Code menggunakan regex
        if (input.matches(".*" + responseCodeRegex + ".*")) {
            responseCode = input.replaceAll(".*" + responseCodeRegex + ".*", "$1");
        }

        // Mencari Error Message menggunakan regex
        if (input.matches(".*" + responseMessageRegex + ".*")) {
            responseMessage = input.replaceAll(".*" + responseMessageRegex + ".*", "$1");
        }

        // Mengembalikan hasil sebagai array
        return new String[] { responseCode, responseMessage };
    }
}