package com.waigo.backend_api.category.application.usecase;

import com.waigo.backend_api.category.application.dto.AddCategoryRequest;
import com.waigo.backend_api.category.application.dto.AddCategoryResponse;
import com.waigo.backend_api.category.application.mapper.CategoryMapper;
import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.category.domain.service.CategorySaver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddCategoryUseCase {

    @Autowired
    private final CategorySaver categorySaver;

    public AddCategoryResponse execute(final AddCategoryRequest addCategoryRequest) {
        final Category category = CategoryMapper.fromAddCategoryRequest(addCategoryRequest);
        final Category storedCategory = categorySaver.execute(category);
        return buildAddCategoryResponse(storedCategory);
    }

    private AddCategoryResponse buildAddCategoryResponse(final Category category) {
        //TODO build AddCategoryResponse
        return null;
    }
}
