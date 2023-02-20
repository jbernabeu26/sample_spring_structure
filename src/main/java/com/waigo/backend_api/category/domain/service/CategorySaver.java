package com.waigo.backend_api.category.domain.service;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.category.domain.port.CategoryRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategorySaver {

    private final CategoryRepositoryInterface categoryRepositoryInterface;

    public Category execute(final Category category) {
        return categoryRepositoryInterface.save(category);
    }
}
