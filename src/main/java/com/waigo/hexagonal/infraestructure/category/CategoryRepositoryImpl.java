package com.waigo.hexagonal.infraestructure.category;

import com.waigo.hexagonal.domain.category.CategoryRepository;
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
        //return jpaCategoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return jpaCategoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll();
    }
}