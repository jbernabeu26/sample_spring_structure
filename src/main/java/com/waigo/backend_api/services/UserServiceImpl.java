package com.waigo.backend_api.services;

import com.waigo.backend_api.model.entities.CustomUser;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.model.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<CustomUser> findAll() {
        return (List<CustomUser>) userRepository.findAll();
    }


    public CustomUser addUser(CustomUser user) {
        return userRepository.save(user);
    }

}
