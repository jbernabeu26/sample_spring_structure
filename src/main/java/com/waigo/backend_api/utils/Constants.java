package com.waigo.backend_api.utils;

public class Constants {
    /**
     * CATEGORY
     **/
    public final static int CATEGORY_NAME_SIZE_MAX = 30;
    public final static int CATEGORY_NAME_SIZE_MIN = 3;

    /**
     * CATEGORY ERROR MESSAGES
     */
    public final static String CATEGORY_NAME_SIZE = "category.name.size";
    public final static String CATEGORY_NAME_NULL = "category.name.null";
    public final static String CATEGORY_NAME_EMPTY = "category.name.empty";
    public final static String CATEGORY_NAME_BLANK = "category.name.blank";


    /**
     * CUSTOM USER
     **/
    public final static int CUSTOM_USER_FIRST_NAME_MAX = 100;
    public final static int CUSTOM_USER_FIRST_NAME_MIN = 2;
    public final static int CUSTOM_USER_LAST_NAME_MAX = 100;
    public final static int CUSTOM_USER_LAST_NAME_MIN = 2;
    public final static int CUSTOM_USER_DESCRIPTION_MAX = 500;

    /**
     * CUSTOM USER ERROR MESSAGES
     */
    public final static String CUSTOM_USER_FIRST_NANE_SIZE = "user.first_name.size";
    public final static String CUSTOM_USER_FIRST_NANE_NULL = "user.first_name.null";
    public final static String CUSTOM_USER_FIRST_NANE_BLANK = "user.first_name.blank";

    public final static String CUSTOM_USER_LAST_NAME_SIZE = "user.last_name.size";
    public final static String CUSTOM_USER_LAST_NAME_NULL = "user.last_name.null";
    public final static String CUSTOM_USER_LAST_NAME_BLANK = "user.last_name.blank";

    public final static String CUSTOM_USER_EMAIL_NULL = "user.email.null";
    public final static String CUSTOM_USER_EMAIL_VALID = "user.email.valid";

    public final static String CUSTOM_USER_DESCRIPTION_SIZE = "user.description.size";

    public final static String CUSTOM_USER_PASSWORD_NULL = "user.password.null";
    public final static String CUSTOM_USER_PASSWORD_BLANK = "user.password.blank";

    /**
     * EVENT
     **/
    public final static int EVENT_NAME_MAX = 100;
    public final static int EVENT_NAME_MIN = 2;
    public final static int EVENT_DESCRIPTION_MIN = 100;
    public final static int EVENT_DESCRIPTION_MAX = 500;

}
