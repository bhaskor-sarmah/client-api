package com.bhaskor.clientapi.util;

public class StringUtil {

    public static String camelToSnake(final String camelStr) {
        String ret = camelStr.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");
        return ret.toLowerCase();
    }

    public static String snakeToCamel(String str) {

        // Run a loop till string
        // string contains underscore
        while (str.contains("_")) {

            // Replace the first occurrence
            // of letter that present after
            // the underscore, to capitalize
            // form of next letter of underscore
            str = str.replaceFirst("_[a-z]", String.valueOf(Character.toUpperCase(str.charAt(str.indexOf("_") + 1))));
        }

        // Return string
        return str;
    }
}
