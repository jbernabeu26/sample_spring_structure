package com.waigo.backend_api.Services;

import com.waigo.backend_api.Model.Entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserService.class)
public class UserServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnDefaultMessageWhenRequestForGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        //Testing service method
        when(userService.findAll()).thenReturn(users);

        //Consuming the endpoint
        this.mockMvc.perform(get("/users/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

}
