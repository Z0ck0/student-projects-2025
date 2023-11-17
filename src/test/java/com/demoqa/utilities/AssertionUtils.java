package com.demoqa.utilities;

import org.testng.Assert;

public class AssertionUtils {

    // Asserts that two values are equal
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    // Asserts that a condition is true
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    // Asserts that a condition is false
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    // Asserts that an object reference is not null
    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    // Asserts that an object reference is null
    public static void assertNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    // Asserts that two values are not equal
    public static void assertNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    // Asserts that a condition is true (no custom message)
    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    // Asserts that a condition is false (no custom message)
    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    // Asserts that two values are equal (no custom message)
    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    // Asserts that an object reference is not null (no custom message)
    public static void assertNotNull(Object object) {
        Assert.assertNotNull(object);
    }
}
