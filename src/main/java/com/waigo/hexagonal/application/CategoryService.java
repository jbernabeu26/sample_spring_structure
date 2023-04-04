package com.waigo.hexagonal.application;



import com.waigo.hexagonal.presentation.category.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO updateCategory(CategoryDTO category);
    void deleteCategory(Long categoryId);
    CategoryDTO getCategoryById(Long categoryId);
    List<CategoryDTO> getAllCategories();
}