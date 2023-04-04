package com.waigo.hexagonal.infraestructure.user;

import com.waigo.hexagonal.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        jpaUserRepository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        //return jpaUserRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return jpaUserRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }
}
