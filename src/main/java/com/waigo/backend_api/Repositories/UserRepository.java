package com.waigo.backend_api.Repositories;

import com.waigo.backend_api.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
