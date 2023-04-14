package com.waigo.backend_api.category.domain.service;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.category.domain.port.CategoryRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySaver {

    private final CategoryRepositoryInterface categoryRepositoryInterface;

    public Category execute(final Category category) {
        return categoryRepositoryInterface.save(category);
    }
}
