package com.waigo.backend_api.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.waigo.backend_api.common.utils.TranslatorExceptions;
import com.waigo.backend_api.common.utils.WException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.category.infrastructure.repository.JpaCategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final JpaCategoryRepository jpaCategoryRepository;
    private final TranslatorExceptions translatorExceptions;


    public CategoryServiceImpl(JpaCategoryRepository injectedCategoryBean, TranslatorExceptions injectedTranslatorException) {

        this.jpaCategoryRepository = injectedCategoryBean;
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
                categoryAdded = jpaCategoryRepository.save(category);
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
        Optional<Category> category = jpaCategoryRepository.findByName(categoryName);
        if(category.isPresent()) {
            result = category.get();
        }
        return result;
    }

    @Override
    public List<Category> findAllCategories() {
        return jpaCategoryRepository.findAll();
    }

    @Override
    public Long deleteCategoryByName(String name) {
        Long idRemoved = 0l;
        idRemoved = jpaCategoryRepository.deleteByName(name);
        return idRemoved;
    }


}
