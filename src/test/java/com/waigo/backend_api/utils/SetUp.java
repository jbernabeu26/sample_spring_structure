package com.waigo.backend_api.utils;

import lombok.Getter;



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

    public SetUp(){};
    
}
