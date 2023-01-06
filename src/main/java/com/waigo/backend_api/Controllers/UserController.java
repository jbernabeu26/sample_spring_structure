package com.waigo.backend_api.Controllers;

import com.waigo.backend_api.Entities.User;
import com.waigo.backend_api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestBody User bodyUser) {
        User user = new User(bodyUser.getFirstName(), bodyUser.getLastName(), bodyUser.getEmail(), bodyUser.getPassword());
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
