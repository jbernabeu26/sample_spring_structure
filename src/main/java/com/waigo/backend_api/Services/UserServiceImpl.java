package com.waigo.backend_api.Services;

import com.waigo.backend_api.Model.Entities.User;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


}
