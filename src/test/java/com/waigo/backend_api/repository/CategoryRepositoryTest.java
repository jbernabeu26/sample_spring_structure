package com.waigo.backend_api.repository;

import com.waigo.backend_api.model.entities.Category;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class CategoryRepositoryTest {
    @Mock
    private CategoryRepository repository;
    private static Validator validator;
    private static Set<ConstraintViolation<Category>> violationsNull;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void createCategoryWithoutData() {

        Category categoryWithOutData = new Category();
        violationsNull = validator.validate(categoryWithOutData);

        when(repository.save(any(Category.class))).thenThrow(new ConstraintViolationException(violationsNull));
        ConstraintViolationException thrown = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            repository.save(categoryWithOutData);
        }, "Incorrect format");

        Assertions.assertEquals(violationsNull.size(), thrown.getConstraintViolations().size());

    }

    @Test
    @Disabled
    public void createCategoryWithWrongName() {

    }

    @Test
    @Disabled
    public void createCategoryWithWrongName2() {

    }

    @Test
    @Disabled
    public void createCategoryWithWrongName3() {

    }

    @Test
    @Disabled
    public void createCategoryGreaterThan30Chars() {

    }

    @Test
    @Disabled
    public void createCategoryGreaterThan30Chars2() {

    }

    @Test
    @Disabled
    public void createCategoryLessThan30Chars() {

    }

    @Test
    @Disabled
    public void createCategoryLessThan30Chars2() {

    }

    @Test
    @Disabled
    public void createCategoryWith30Chars() {

    }

    @Test
    @Disabled
    public void createCategoryWithExistentName() {

    }

    @Test
    @Disabled
    public void updateCategoryWithEmptyName() {

    }

    @Test
    @Disabled
    public void updateCategoryWithBlankName() {

    }

    @Test
    @Disabled
    public void updateCategoryWithNullName() {

    }

    @Test
    @Disabled
    public void updateCategoryWithExistentName() {

    }

    @Test
    @Disabled
    public void updateCategoryName() {

    }

    @Test
    @Disabled
    public void deleteCategoryById() {

    }

    @Test
    @Disabled
    public void deleteCategoryByUnexistentId() {

    }

    @Test
    @Disabled
    public void deleteCategoryByUnexistentName() {

    }

    @Test
    @Disabled
    public void deleteCategoryByName() {

    }

    @Test
    @Disabled
    public void findCategoryByUnexistentId() {

    }

    @Test
    @Disabled
    public void findCategoryById() {

    }

    @Test
    @Disabled
    public void findCategoryByUnexistentName() {

    }

    @Test
    @Disabled
    public void findCategoryByName() {

    }


}
