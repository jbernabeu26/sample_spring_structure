package com.waigo.backend_api.services;

import com.waigo.backend_api.model.entities.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);
    Category findCategory(String name);

    List<Category> findAllCategories();
    Long deleteCategoryByName(String name);



}
