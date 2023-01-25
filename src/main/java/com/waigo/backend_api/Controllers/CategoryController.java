package com.waigo.backend_api.Controllers;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Services.CategoryServiceImpl;

import com.waigo.backend_api.Utils.TranslatorExceptions;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    TranslatorExceptions translatorExceptions;

    @Autowired
    @Qualifier("messageSource")
    MessageSource messageSource;


    @Autowired
    public CategoryController(CategoryServiceImpl injectedBean) {this.categoryService = injectedBean;}


    @PostMapping(path = "/add")
    public @ResponseBody Map<String,Object> addNewCategory(@RequestBody Category body) {
        Map<String,Object> result = new HashMap<>();
        try {
            Category newCategory = categoryService.addCategory(body);
            result.put("status","saved");
            result.put("data",newCategory);
        } catch (ConstraintViolationException exception) {
            List<String> errors = Arrays.asList(exception.getMessage().split(","));
            List<String> translatedErrors = translatorExceptions.translateExceptionMessages(errors);
            result.put("status","error");
            result.put("error", translatedErrors);
            return result;
        }

        return result;


    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return categoryService.findAll();
    }


}

