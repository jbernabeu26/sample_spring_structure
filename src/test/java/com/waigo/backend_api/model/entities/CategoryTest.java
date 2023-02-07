package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;




@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        categoryRepository.deleteAll();
    }

    @Test
    public void createCategoryWithoutData(){
        final Category category = new Category();
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_null");
    }

    @Test
    public void createCategoryWithWrongName(){
        final Category category = new Category(null);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_null");

    }
    @Test
    public void createCategoryWithWrongName2(){

        final Category category = new Category("");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_empty");
    }

    @Test
    public void createCategoryWithWrongName3(){
        final Category category = new Category("     ");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_blank");

    }

    @Test
    public void createCategoryGreaterThan30Chars(){
        String name40Chars = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Category category = new Category(name40Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryGreaterThan30Chars2(){

        String name70Chars = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Category category = new Category(name70Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");
    }

    @Test
    public void createCategoryLessThan30Chars(){
        String name29Chars = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Category category = new Category(name29Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name29Chars));
    }

    @Test
    public void createCategoryLessThan30Chars2(){
        String name10Chars = "aaaaaaaaaa";
        Category category = new Category(name10Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name10Chars));
    }

    @Test
    public void createCategoryWith30Chars(){
        String name30Chars = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Category category = new Category(name30Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name30Chars));
    }

    @Test
    public void createCategoryWith2Chars(){
        String name2Chars = "aa";
        Category category = new Category(name2Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryWith3Chars(){
        String name3Chars = "aaa";
        Category category = new Category(name3Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name3Chars));
    }

}
