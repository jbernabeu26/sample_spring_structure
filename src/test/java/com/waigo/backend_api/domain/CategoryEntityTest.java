package com.waigo.backend_api.domain;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.hexagonal.domain.category.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryEntityTest {

    @Test
    public void createCategoryWithoutData(){
        final Category category = new Category();
        Assertions.assertTrue(category!=null);
    }
}
