package com.waigo.backend_api.utils;

import com.waigo.backend_api.model.entities.Category;
import com.waigo.backend_api.model.entities.CustomUser;
import com.waigo.backend_api.model.entities.Event;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class SetUp {

    /** SETUP FOR CATEGORY TESTS **/
    @Getter private final String category_name_blank = new String(new char[8]).replace('\0',' ');
    @Getter private final String category_name_40_Chars = new String(new char[40]).replace('\0','a');
    @Getter private final String category_name_70_Chars = new String(new char[70]).replace('\0','a');
    @Getter private final String category_name_29_Chars = new String(new char[29]).replace('\0','a');
    @Getter private final String category_name_10_Chars = new String(new char[10]).replace('\0','a');
    @Getter private final String category_name_30_Chars = new String(new char[30]).replace('\0','a');
    @Getter private final String category_name_2_Chars = new String(new char[2]).replace('\0','a');
    @Getter private final String category_name_3_Chars = new String(new char[3]).replace('\0','a');


    /** SET UP FOR CUSTOM USER TESTS**/
    @Getter private final String validDescription = new String(new char[250]).replace("\0", "a");
    @Getter private final String validPassword = "password";
    @Getter private final String validFirstName = "John";
    @Getter private final String validLastName = "Doe";
    @Getter private final String validEmail = "john.doe@mail.com";
    @Getter private final CustomUser validCustomUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);

    /** SET UP FOR EVENT TESTS */
    @Getter final LocalDateTime validStartDate = LocalDateTime.of(2023, 12, 12, 12, 0);
    @Getter final LocalDateTime validEndDate = LocalDateTime.of(2023, 12, 12, 14, 30);
    @Getter final Integer validMaxParticipants = 50;
    @Getter final String[] validGeolocation = {"40.009656","-105.244660"};
    @Getter final Event.PrivacyStatus validPrivacy = Event.PrivacyStatus.MIXED;
    @Getter final Set<Category> validCategorySet =  new HashSet<>(Arrays.asList(new Category("Furbito"), new Category("Escalada")));



    public SetUp(){};
    
}
