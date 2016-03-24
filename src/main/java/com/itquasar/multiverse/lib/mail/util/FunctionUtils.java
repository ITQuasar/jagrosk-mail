package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.exception.NullArgumentException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class FunctionUtils {

    private FunctionUtils() {
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

    public static <T> List<T> safeArrayToList(T[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(array);
        }
    }
}
