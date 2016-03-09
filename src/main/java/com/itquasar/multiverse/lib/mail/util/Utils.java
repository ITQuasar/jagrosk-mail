package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.exception.NullArgumentException;

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

    public static void checkNullArgument(Object argument, String argumentName) {
        if (argument == null) {
            throw new NullArgumentException(argumentName);
        }
    }

}
