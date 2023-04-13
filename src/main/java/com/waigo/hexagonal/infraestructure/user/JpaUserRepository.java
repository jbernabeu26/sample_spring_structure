package com.waigo.hexagonal.infraestructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.waigo.hexagonal.domain.user.User;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
}