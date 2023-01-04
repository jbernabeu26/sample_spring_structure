package com.waigo.backend_api.Repositories;

import com.waigo.backend_api.Entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
