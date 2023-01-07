package com.waigo.backend_api.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.Model.Entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
