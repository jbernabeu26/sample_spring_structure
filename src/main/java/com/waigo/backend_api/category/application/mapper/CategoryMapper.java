package com.waigo.backend_api.category.application.mapper;

import com.waigo.backend_api.category.application.dto.AddCategoryRequest;
import com.waigo.backend_api.category.domain.entity.Category;
import org.springframework.stereotype.Service;



public class CategoryMapper {

    public static Category fromAddCategoryRequest(final AddCategoryRequest addCategoryRequest) {
        return Category.builder()
                .id(addCategoryRequest.id())
                .name(addCategoryRequest.name())
                .build();

    }
}
