package com.waigo.backend_api.Services;

import java.util.List;
import java.util.Set;

import com.waigo.backend_api.Utils.TranslatorExceptions;
import com.waigo.backend_api.Utils.WException;
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
    private final TranslatorExceptions translatorExceptions;

    @Autowired
    public CategoryServiceImpl(CategoryRepository injectedCategoryBean,TranslatorExceptions injectedTranslatorException) {

        this.categoryRepository = injectedCategoryBean;
        this.translatorExceptions = injectedTranslatorException;
    }


    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Override
    public Category addCategory(Category category) {

        Category categoryAdded = new Category();
        try{
            Set<ConstraintViolation<Category>> violationSet = validator.validate(category);
            if (!violationSet.isEmpty()) {
                throw new ConstraintViolationException(violationSet);
            }else{
                categoryAdded = categoryRepository.save(category);
            }
        // If there are an error, we create and throw our own exception
        }catch (ConstraintViolationException exception){
            String codeError = exception.getConstraintViolations().iterator().next().getMessage();
            String messageError = translatorExceptions.translateExceptionMessage(codeError);
            throw new WException("1000",messageError);
        }catch (Exception exception){
            throw new WException("1001","Failed to create category");
        }



        return categoryAdded;
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

}
