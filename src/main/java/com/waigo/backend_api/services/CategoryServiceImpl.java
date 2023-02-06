package com.waigo.backend_api.services;

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
            throw new WException("1000",messageError);
        }catch (Exception exception){
            throw new WException("1001","Failed to create category");
        }


        return categoryAdded;
    }


}
