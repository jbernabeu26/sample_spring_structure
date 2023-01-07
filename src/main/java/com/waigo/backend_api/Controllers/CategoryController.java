package com.waigo.backend_api.Controllers;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Services.CategoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired(required = true)
    CategoryServiceImpl categoryService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewCategory(@RequestBody Category body) {

        categoryService.addCategory(body);
        return "Saved";


    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return categoryService.findAll();
    }


}

