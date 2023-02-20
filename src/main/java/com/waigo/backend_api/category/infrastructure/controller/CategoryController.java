package com.waigo.backend_api.category.infrastructure.controller;

import com.waigo.backend_api.category.application.dto.AddCategoryRequest;
import com.waigo.backend_api.category.application.dto.AddCategoryResponse;
import com.waigo.backend_api.category.application.usecase.AddCategoryUseCase;
import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.services.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final AddCategoryUseCase addCategoryUseCase;

//    @Autowired
//    public CategoryController(CategoryServiceImpl injectedBean) {this.categoryService = injectedBean;}

    @PostMapping(path = "/add")
    public ResponseEntity<AddCategoryResponse> addNewCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest) {
//        Map<String,Object> result = new HashMap<>();
//        Category newCategory = categoryService.addCategory(body);
//        result.put("status","saved");
//        result.put("data",newCategory);
//
//        return result;
        final AddCategoryResponse addCategoryResponse = addCategoryUseCase.execute(addCategoryRequest);
        return ResponseEntity.ok(addCategoryResponse);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"test");
        //return categoryService.findAll();
    }

}

