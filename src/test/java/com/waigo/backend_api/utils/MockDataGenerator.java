package com.waigo.backend_api.utils;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.user.domain.aggregate.CustomUser;
import com.waigo.backend_api.event.domain.aggregate.Event;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;


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

    @Getter private final CustomUser validCustomUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);

    /** SET UP FOR EVENT TESTS **/
    @Getter final LocalDateTime validStartDate = LocalDateTime.of(2023, 12, 12, 12, 0);
    @Getter final LocalDateTime validEndDate = LocalDateTime.of(2023, 12, 12, 14, 30);
    @Getter final Integer validMaxParticipants = 50;
    @Getter final List<String> validGeolocation = Arrays.asList("40.009656","-105.244660");
    @Getter final Event.PrivacyStatus validPrivacy = Event.PrivacyStatus.MIXED;
    @Getter final Set<Category> validCategorySet =  new HashSet<>(Arrays.asList(getRandomCategory(10), getRandomCategory(10)));

    public static Category getRandomCategory(int length){

        Category new_category;
        Random random = new Random();
        char[] name = new char[length];
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            name[i] = c;
        }
        new_category = new Category(String.valueOf(name));
        return new_category;
    }

    public MockDataGenerator() {
    }


}
