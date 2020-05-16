package com.ale.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;


import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class FakerDemo {
    @Test
    public void whenBothifyCalled_checkPatternMatches() throws Exception {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        // It works by replacing ‘?’ with random letters and ‘#’ with random numbers.
        String email = fakeValuesService.bothify("????##@gmail.com");
        System.out.println(email);
        Matcher emailMatcher = Pattern.compile("\\w{4}\\d{2}@gmail.com").matcher(email);

        assertTrue(emailMatcher.find());
    }

    @Test
    public void givenValidService_whenRegexifyCalled_checkPattern() throws Exception {

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String alphaNumericString = fakeValuesService.regexify("[a-z1-9]{10}");
        System.out.println(alphaNumericString);
        Matcher alphaNumericMatcher = Pattern.compile("[a-z1-9]{10}").matcher(alphaNumericString);

        assertTrue(alphaNumericMatcher.find());
    }

    @Test
    public void testFaker() {
        Faker faker = new Faker();
        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();

        System.out.println(String.format("%s\n%s\n%s\n%s",
                                         number,
                                         streetName,
                                         city,
                                         country));
    }

}
