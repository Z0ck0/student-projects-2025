package com.demoqa.utilities;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

    private static final Faker faker = new Faker();


    public static String getRandomFirstName() {
        return faker.name().firstName();
    }


    public static String getRandomLastName() {
        return faker.name().lastName();
    }


    public static String getRandomFullName() {
        return faker.name().fullName();
    }


    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }


    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }



    public static String getRandomPassword() {
        return faker.internet().password(8, 12);
    }


    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }


    public static String getRandomCellNumber() {
        return faker.phoneNumber().cellPhone();
    }
}
