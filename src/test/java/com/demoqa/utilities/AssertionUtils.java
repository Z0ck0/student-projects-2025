package com.demoqa.utilities;

import com.google.inject.matcher.Matcher;
import org.testng.Assert;


public class AssertionUtils {

    // Asserts that two values are equal, retrying up to `maxAttempts` times with a 500 mill-second delay between attempts.
    public static void assertEquals(Object actual, Object expected, String message, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                Assert.assertEquals(actual, expected, message);
                return; // Assertion passed, exit the loop
            } catch (AssertionError e) {
                // Assertion failed, log the error and continue retrying
                System.out.println("Assertion failed on attempt " + (attempt + 1) + ": " + message);
            }
            // Wait for a specified interval before retrying
            waitInSeconds(); // Adjust the wait time as needed
        }

        // If all retries fail, fail the test with the original message
        Assert.assertEquals(actual, expected, "After " + maxAttempts + " attempts: " + message);
    }


    // Asserts that a condition is true, retrying up to `maxAttempts` times with a 500 mill-second delay between attempts.
    public static void assertTrue(boolean condition, String message, int maxAttempts) throws InterruptedException {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            if (condition) {
                return; // Condition is true, exit the loop
            }
            // Condition is false, log the message and continue retrying
            System.out.println("Assertion failed on attempt " + (attempt + 1) + ": " + message);

            // Wait for a specified interval before retrying
            waitInSeconds(); // Adjust the wait time as needed
        }

        // If all retries fail, fail the test with the original message
        Assert.assertTrue(condition, message);
    }



    // Assertion using assertThat()
    public static <T> void assertThat(String message, Matcher<? super T> matcher, T actual) {
        try {
            assertThat(message, matcher, actual);
        } catch (AssertionError e) {
            Assert.fail("Assertion failed: " + message, e);
        }
    }







    // Asserts that a condition is false
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    // Asserts that two values are not equal
    public static void assertNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }


    // Asserts that a condition is false (no custom message)
    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }


    // Asserts that an object reference is not null (no custom message)
    public static void assertNotNull(Object object) {
        Assert.assertNotNull(object);
    }



    // Helper method for waiting
    private static void waitInSeconds() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
