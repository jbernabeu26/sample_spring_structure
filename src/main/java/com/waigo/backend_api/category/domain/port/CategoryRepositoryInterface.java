package com.waigo.backend_api.category.domain.port;

import com.waigo.backend_api.category.domain.entity.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryInterface {

    Optional<Category> findByName(String name);
    List<Category> findAll();
    Long deleteByName(String name);
    Category save(Category category);

}
