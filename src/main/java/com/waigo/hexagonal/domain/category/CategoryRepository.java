package com.waigo.hexagonal.domain.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);

    void deleteById(Long categoryId);

    Category findById(Long categoryId);

    List<Category> findAll();
}