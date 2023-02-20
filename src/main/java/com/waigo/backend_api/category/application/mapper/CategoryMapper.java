package com.waigo.backend_api.category.application.mapper;

import com.waigo.backend_api.category.application.dto.AddCategoryRequest;
import com.waigo.backend_api.category.domain.aggregate.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public static Category fromAddCategoryRequest(final AddCategoryRequest addCategoryRequest) {
        return new Category(addCategoryRequest.id(),
                addCategoryRequest.name(),
                addCategoryRequest.events());
    }
}
