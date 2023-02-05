package com.waigo.backend_api.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.Model.Entities.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {

}
