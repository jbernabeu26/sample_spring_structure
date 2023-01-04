package com.waigo.backend_api.Controllers;

import com.waigo.backend_api.Entities.Category;
import com.waigo.backend_api.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewCategory(@RequestBody Category body) {
        Category category = new Category(body.getName());
        categoryRepository.save(category);
        return "Saved";


    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/f")
    public @ResponseBody String f() {
        return "Saved";
    }
}

