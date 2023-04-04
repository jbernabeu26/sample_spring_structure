package com.waigo.hexagonal.domain.user;

import java.util.List;

public interface UserRepository {
    User save(User user);

    void deleteById(Long userId);

    User findById(Long userId);

    List<User> findAll();
}