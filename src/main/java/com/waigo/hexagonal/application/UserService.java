package com.waigo.hexagonal.application;

import com.waigo.hexagonal.presentation.event.EventDTO;
import com.waigo.hexagonal.presentation.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user);
    void deleteUser(Long userId);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    List<EventDTO> getEventsByUser(UserDTO user);
}