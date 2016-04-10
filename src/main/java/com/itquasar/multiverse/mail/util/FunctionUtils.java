package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.exception.NullArgumentException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class FunctionUtils {

    private FunctionUtils() {
    }

    @FunctionalInterface
    public static interface SupplierWithException<T> {

        public T get() throws Exception;

    }

    public static <T> T defaultOnNullOrException(SupplierWithException<T> supplier, T defaultValue, Logger logger) {
        T value = null;
        try {
            value = supplier.get();
        } catch (Exception e) {
            logger.error("Error while acessing a value", e);
        }
        return defaultOnNull(value, defaultValue);
    }

    public static <T> T defaultOnNull(T valueToTest, T defaultValue) {
        return valueToTest == null ? defaultValue : valueToTest;
    }

    public static String emptyOnNull(String valueToTest) {
        return defaultOnNull(valueToTest, Constants.EMPTY_STRING);
    }

    public static <T> List<T> emptyOnNull(List<T> valueToTest) {
        return defaultOnNull(valueToTest, Collections.EMPTY_LIST);
    }

    public static void throwExceptionOnNullArgument(Object argument, String argumentName) {
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

    public static <T> List<T> toList(T... values) {
        List<T> list = new LinkedList<>();
        if (values != null && !(values.length == 1 && values[0] == null)) {
            for (T value : values) {
                list.add(value);
            }
        }
        return list;
    }
}
