package com.waigo.backend_api.Controllers;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Services.CategoryServiceImpl;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl injectedBean) {
        this.categoryService = injectedBean;
    }


    @PostMapping(path = "/add")
    public @ResponseBody String addNewCategory(@RequestBody Category body) {
        try {
            categoryService.addCategory(body);
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }


        return "Saved";


    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return categoryService.findAll();
    }


}

