package com.waigo.backend_api.controllers;

import com.waigo.backend_api.model.entities.Category;
import com.waigo.backend_api.services.CategoryServiceImpl;
import com.waigo.backend_api.utils.WException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl injectedBean) {this.categoryService = injectedBean;}


    @PostMapping(path = "/add")
    public @ResponseBody Map<String,Object> addNewCategory(@Valid @RequestBody Category body) {
        Map<String,Object> result = new HashMap<>();
        Category newCategory = categoryService.addCategory(body);
        result.put("status","saved");
        result.put("data",newCategory);

        return result;


    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"test");
        //return categoryService.findAll();
    }


}

