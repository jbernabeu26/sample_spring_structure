package com.waigo.backend_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;
}
