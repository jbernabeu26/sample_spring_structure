package com.waigo.backend_api.Controllers;


import com.waigo.backend_api.Model.Entities.CustomUser;
import com.waigo.backend_api.Services.UserService;
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


}
