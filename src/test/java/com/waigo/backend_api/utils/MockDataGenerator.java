package com.waigo.backend_api.utils;

import lombok.Getter;


public class MockDataGenerator {
    /**
     * SETUP FOR CATEGORY TESTS
     **/
    public final String generateNameWithCustomChars(int charsLength) {
        return new String(new char[charsLength]).replace('\0', 'a');
    }

    public final String generateNameWithCustomChars(int charsLength, char customChar) {
        return new String(new char[charsLength]).replace('\0', customChar);
    }

    /**
     * SET UP FOR CUSTOM USER TESTS
     **/
    @Getter
    private final String validDescription = new String(new char[250]).replace("\0", "a");
    @Getter
    private final String validPassword = "password";
    @Getter
    private final String validFirstName = "John";
    @Getter
    private final String validLastName = "Doe";
    @Getter
    private final String validEmail = "john.doe@mail.com";

    public MockDataGenerator() {
    }


}
