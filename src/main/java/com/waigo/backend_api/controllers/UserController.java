package com.waigo.backend_api.controllers;


import com.waigo.backend_api.model.entities.CustomUser;
import com.waigo.backend_api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;


    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<CustomUser> findAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public CustomUser addNewUser(@Valid @RequestBody CustomUser body) {
        System.out.println("addNewUser " + body);
        return userService.addUser(body);
    }


}
