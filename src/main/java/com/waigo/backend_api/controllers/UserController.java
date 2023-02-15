package com.waigo.backend_api.controllers;


import com.waigo.backend_api.model.entities.CustomUser;
import com.waigo.backend_api.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService injectedBean) {
        this.userService = injectedBean;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public @ResponseBody List<CustomUser> findAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public @ResponseBody CustomUser addNewUser(@RequestBody CustomUser body) {
        return userService.addUser(body);
    }


}
