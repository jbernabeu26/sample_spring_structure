package com.waigo.backend_api.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.waigo.backend_api.utils.TranslatorExceptions;
import com.waigo.backend_api.utils.WException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.model.entities.Category;
import com.waigo.backend_api.model.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final TranslatorExceptions translatorExceptions;


    public CategoryServiceImpl(CategoryRepository injectedCategoryBean,TranslatorExceptions injectedTranslatorException) {

        this.categoryRepository = injectedCategoryBean;
        this.translatorExceptions = injectedTranslatorException;
    }


    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Override
    public Category addCategory(Category category) {

        Category categoryAdded;
        try{
            Set<ConstraintViolation<Category>> violationSet = validator.validate(category);
            if (!violationSet.isEmpty()) {
                throw new ConstraintViolationException(violationSet);
            }else{
                categoryAdded = categoryRepository.save(category);
            }
        // If there is an error, we create and throw our own exception
        }catch (ConstraintViolationException exception){
            String codeError = exception.getConstraintViolations().iterator().next().getMessage();
            String messageError = translatorExceptions.translateExceptionMessage(codeError);
            throw new WException(messageError);
        }catch (Exception exception){
            throw new WException("Failed to create category");
        }


        return categoryAdded;
    }

    @Override
    public Category findCategory(String categoryName){
        Category result = null;
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if(category.isPresent()) {
            result = category.get();
        }
        return result;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Long deleteCategoryByName(String name) {
        Long idRemoved = 0l;
        idRemoved = categoryRepository.deleteByName(name);
        return idRemoved;
    }


}
