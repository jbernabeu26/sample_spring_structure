package com.waigo.backend_api.Services;

import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Model.Repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryBean) {
        this.categoryRepository = categoryBean;
    }


    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Override
    public Category addCategory(Category category) {
        Set<ConstraintViolation<Category>> violationSet = validator.validate(category);
        if (!violationSet.isEmpty()) {
            throw new ConstraintViolationException(violationSet);
        }


        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

}
