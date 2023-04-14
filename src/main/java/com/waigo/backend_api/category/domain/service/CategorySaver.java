package com.waigo.backend_api.category.domain.service;

import com.waigo.backend_api.category.domain.entity.Category;
import com.waigo.backend_api.category.domain.port.CategoryRepositoryInterface;
import com.waigo.backend_api.category.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySaver {

    private final CategoryRepository categoryRepository;

    public Category execute(final Category category) {
        return categoryRepository.save(category);
    }
}
