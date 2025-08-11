package com.testautomation.utils.data;

import com.github.javafaker.Faker;

public class RandomDataGenerator {
    private static final Faker faker = new Faker();
    
    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    public static String getRandomPassword() {
        return faker.internet().password();
    }
    
    public static String getRandomFirstName() {
        return faker.name().firstName();
    }
    
    public static String getRandomLastName() {
        return faker.name().lastName();
    }
    
    public static String getRandomFullName() {
        return faker.name().fullName();
    }
    
    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    
    public static String getRandomCellNumber() {
        return faker.phoneNumber().cellPhone();
    }
    
    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }
    
    public static String getRandomCity() {
        return faker.address().city();
    }
    
    public static String getRandomState() {
        return faker.address().state();
    }
    
    public static String getRandomZipCode() {
        return faker.address().zipCode();
    }
    
    public static String getRandomCompany() {
        return faker.company().name();
    }
    
    public static String getRandomJobTitle() {
        return faker.job().title();
    }
}
