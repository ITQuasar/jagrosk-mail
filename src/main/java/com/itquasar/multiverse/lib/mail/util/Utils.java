package com.itquasar.multiverse.lib.mail.util;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Utils {

    private Utils() {
    }

    public static <T> T defaultOnNull(T valueToTest, T defaultValue) {
        return valueToTest == null ? defaultValue : valueToTest;
    }

    public static String emptyOnNull(String valueToTest) {
        return defaultOnNull(valueToTest, Constants.EMPTY_STRING);
    }
}
