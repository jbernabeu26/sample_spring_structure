package com.waigo.backend_api.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.model.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findByName(String name);

}
