package com.waigo.hexagonal.infraestructure.category;

import com.waigo.hexagonal.domain.category.CategoryRepository;
import com.waigo.hexagonal.domain.category.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Category save(Category category) {
        return jpaCategoryRepository.save(category);
    }

    @Override
    public void deleteById(Long categoryId) {
        jpaCategoryRepository.deleteById(categoryId);
    }

    @Override
    public Category findById(Long categoryId) {
        return jpaCategoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll();
    }
}