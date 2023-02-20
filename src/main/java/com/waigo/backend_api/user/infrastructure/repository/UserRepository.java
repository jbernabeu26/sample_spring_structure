package com.waigo.backend_api.user.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.user.domain.aggregate.CustomUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {
    Optional<CustomUser> findByEmail(String email);

    void deleteByEmail(String email);

}
