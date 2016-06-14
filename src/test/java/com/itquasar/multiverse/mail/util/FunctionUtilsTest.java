package com.itquasar.multiverse.mail.util;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class FunctionUtilsTest {

    /**
     * Test of defaultOnNullOrException method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testDefaultOnNullOrException() {
        System.out.println("defaultOnNullOrException");
    }

    /**
     * Test of defaultOnNull method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testDefaultOnNull() {
        System.out.println("defaultOnNull");
    }

    /**
     * Test of emptyOnNull method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testEmptyOnNull_String() {
        System.out.println("emptyOnNull");
    }

    /**
     * Test of emptyOnNull method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testEmptyOnNull_List() {
        System.out.println("emptyOnNull");
    }

    /**
     * Test of throwExceptionOnNullArgument method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testThrowExceptionOnNullArgument() {
        System.out.println("throwExceptionOnNullArgument");
    }

    /**
     * Test of safeArrayToList method, of class FunctionUtils.
     */
    @Test
    @Ignore
    public void testSafeArrayToList() {
        System.out.println("safeArrayToList");
    }

    /**
     * Test of toList method, of class FunctionUtils.
     */
    @Test
    public void testToList() {
        System.out.println("toList");
        String val1 = "one", val2 = "two";

        List<String> list = new LinkedList<>();

        assertEquals(list, FunctionUtils.toList((String) null));

        list.add(val1);
        assertEquals(list, FunctionUtils.toList(val1));

        list.add(val2);
        assertEquals(list, FunctionUtils.toList(val1, val2));
        assertEquals(list, FunctionUtils.toList(new String[]{val1, val2}));
    }

}
