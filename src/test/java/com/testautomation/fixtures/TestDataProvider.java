package com.testautomation.fixtures;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    
    @DataProvider(name = "userRegistrationData")
    public static Object[][] getUserRegistrationData() {
        return new Object[][] {
            {"John", "Doe", "john.doe@example.com", "1234567890", "Male", "123 Main St"},
            {"Jane", "Smith", "jane.smith@example.com", "0987654321", "Female", "456 Oak Ave"},
            {"Bob", "Johnson", "bob.johnson@example.com", "5555555555", "Male", "789 Pine Rd"}
        };
    }
    
    @DataProvider(name = "browserData")
    public static Object[][] getBrowserData() {
        return new Object[][] {
            {"chrome"},
            {"firefox"},
            {"edge"}
        };
    }
    
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][] {
            {"testuser1@example.com", "password123"},
            {"testuser2@example.com", "password456"},
            {"testuser3@example.com", "password789"}
        };
    }
    
    @DataProvider(name = "buttonTestData")
    public static Object[][] getButtonTestData() {
        return new Object[][] {
            {"Double Click", "Message should appear"},
            {"Right Click", "Message should appear"},
            {"Single Click", "Message should appear"}
        };
    }
}
