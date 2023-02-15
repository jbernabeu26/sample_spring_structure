package com.waigo.backend_api.services;

import com.waigo.backend_api.utils.MockDataGenerator;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(UserService.class)
public class CustomUserServiceTests {

    @MockBean
    private UserService userService;

    private MockDataGenerator data;


    //Testing create user operations

    @Test
    public void createUserWithWrongBlankFirstName(){


    }


}
