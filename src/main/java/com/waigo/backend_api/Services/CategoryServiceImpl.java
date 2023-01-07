package com.waigo.backend_api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Model.Repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
        
    }

    public List<Category> findAll(){
        return (List<Category>) categoryRepository.findAll();
    }
    
}
