package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.exception.NullArgumentException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class FunctionUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(FunctionUtils.class);

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
        return toCollection(LinkedList.class, values);
    }

    public static <T> Set<T> toSet(T... values) {
        return toCollection(HashSet.class, values);
    }

    public static <C extends Collection, T> C toCollection(Class<C> collectionClass, T... values) {
        try {
            C collection = collectionClass.newInstance();
            if (values != null && !(values.length == 1 && values[0] == null)) {
                for (T value : values) {
                    collection.add(value);
                }
            }
            return collection;
        } catch (InstantiationException | IllegalAccessException ex) {
            String msg = "Error instantiating given collection class '" + collectionClass.getCanonicalName() + "'.";
            LOGGER.error(msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }

}
