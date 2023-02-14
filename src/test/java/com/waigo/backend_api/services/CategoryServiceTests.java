package com.waigo.backend_api.services;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.entities.Category;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import com.waigo.backend_api.utils.SetUp;
import com.waigo.backend_api.utils.WException;
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
    CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryService;
    private SetUp data;
    @Before
    public void setUp(){
        data = new SetUp();
        categoryRepository.saveAndFlush(new Category(data.getCategory_name_3_Chars()));
        categoryRepository.saveAndFlush(new Category(data.getCategory_name_30_Chars()));
        categoryRepository.saveAndFlush(new Category(data.getCategory_name_29_Chars()));

    }
    @Test
    public void createCategoryWithWrongName(){

        WException thrown = Assertions.assertThrows(WException.class,
                () -> categoryService.addCategory(new Category(data.getCategory_name_blank())));
        System.out.println(thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains("El nombre no deberia estar en blanco"));

    }
    @Test
    public void createCategory(){
        Category categoryCreated = categoryService.addCategory(new Category(data.getCategory_name_10_Chars()));
        Assertions.assertAll(
                () -> Assertions.assertNotNull(categoryCreated),
                () -> Assertions.assertEquals(categoryCreated.getName(), data.getCategory_name_10_Chars()),
                () -> Assertions.assertNotNull(categoryRepository.findByName(data.getCategory_name_10_Chars()))
        );


    }

    @Test
    public void findCategoryUnexistent(){
        Category categoryFound = categoryService.findCategory(data.getCategory_name_10_Chars());
        Assertions.assertNull(categoryFound);

    }

    @Test
    public void findCategory(){
        Category categoryFound = categoryService.findCategory(data.getCategory_name_3_Chars());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(categoryFound),
                () -> Assertions.assertEquals(categoryFound.getName(),data.getCategory_name_3_Chars())

        );
    }

    @Test
    public void findAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertEquals(categories.size(),3);
    }

    @Test
    public void deleteCategoryByNameUnexistent(){
        Long categoryIdRemoved = categoryService.deleteCategoryByName(data.getCategory_name_2_Chars());
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertAll(
                () -> Assertions.assertEquals(categoryIdRemoved,0),
                () -> Assertions.assertEquals(categories.size(),3)
        );

    }

    @Test
    public void deleteCategoryByName(){
        Long categoryIdRemoved = categoryService.deleteCategoryByName(data.getCategory_name_3_Chars());
        List<Category> categories = categoryService.findAllCategories();
        Assertions.assertAll(
                () -> Assertions.assertTrue(categoryIdRemoved>0),
                () -> Assertions.assertEquals(categories.size(),2)
        );
    }

}
