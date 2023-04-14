package com.waigo.backend_api.services;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.category.domain.entity.Category;
import com.waigo.backend_api.category.infrastructure.repository.JpaCategoryRepository;
import com.waigo.backend_api.utils.MockDataGenerator;
import com.waigo.backend_api.common.utils.WException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTests {

    @Autowired
    JpaCategoryRepository jpaCategoryRepository;
    @Autowired
    private CategoryServiceImpl categoryService;
    private MockDataGenerator data;
    @Before
    public void setUp(){
        data = new MockDataGenerator();
        jpaCategoryRepository.saveAndFlush(new Category(data.generateNameWithCustomChars(3)));
        jpaCategoryRepository.saveAndFlush(new Category(data.generateNameWithCustomChars(30)));
        jpaCategoryRepository.saveAndFlush(new Category(data.generateNameWithCustomChars(29)));

    }
    @Test
    public void createCategoryWithWrongName(){
        char blank = ' ';

        WException thrown = Assertions.assertThrows(WException.class,
                () -> categoryService.addCategory(new Category(data.generateNameWithCustomChars(8, blank))));
        System.out.println(thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains("El nombre no deberia estar en blanco"));

    }
    @Test
    public void createCategory(){
        Category categoryCreated = categoryService.addCategory(new Category(data.generateNameWithCustomChars(10)));
        Assertions.assertAll(
                () -> Assertions.assertNotNull(categoryCreated),
                () -> Assertions.assertEquals(categoryCreated.getName(), data.generateNameWithCustomChars(10)),
                () -> Assertions.assertNotNull(jpaCategoryRepository.findByName(data.generateNameWithCustomChars(10)))
        );


    }

    @Test
    public void findCategoryUnexistent(){
        Category categoryFound = categoryService.findCategory(data.generateNameWithCustomChars(10));
        Assertions.assertNull(categoryFound);

    }

    @Test
    public void findCategory(){
        Category categoryFound = categoryService.findCategory(data.generateNameWithCustomChars(3));
        Assertions.assertAll(
                () -> Assertions.assertNotNull(categoryFound),
                () -> Assertions.assertEquals(categoryFound.getName(),data.generateNameWithCustomChars(3))

        );
    }

    @Test
    public void findAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertEquals(categories.size(),3);
    }

    @Test
    public void deleteCategoryByNameUnexistent(){
        Long categoryIdRemoved = categoryService.deleteCategoryByName(data.generateNameWithCustomChars(2));
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertAll(
                () -> Assertions.assertEquals(categoryIdRemoved,0),
                () -> Assertions.assertEquals(categories.size(),3)
        );

    }

    @Test
    public void deleteCategoryByName(){
        Long categoryIdRemoved = categoryService.deleteCategoryByName(data.generateNameWithCustomChars(3));
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertAll(
                () -> Assertions.assertTrue(categoryIdRemoved>0),
                () -> Assertions.assertEquals(categories.size(),2)
        );
    }

}
