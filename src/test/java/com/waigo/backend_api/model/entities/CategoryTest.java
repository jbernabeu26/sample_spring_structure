package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import com.waigo.backend_api.utils.MockDataGenerator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    private final MockDataGenerator data = new MockDataGenerator();

    @BeforeAll
    public void setUp(){
        categoryRepository.saveAndFlush(new Category("sports"));
        categoryRepository.saveAndFlush(new Category("study"));
        categoryRepository.saveAndFlush(new Category("party"));
        categoryRepository.saveAndFlush(new Category("work"));
        categoryRepository.saveAndFlush(new Category("business"));

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
        var blank = ' ';
        final Category category = new Category(data.generateNameWithCustomChars(8, blank));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_blank");

    }

    @Test
    public void createCategoryGreaterThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(40));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryGreaterThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(70));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");
    }

    @Test
    public void createCategoryLessThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(29));
        categoryRepository.saveAndFlush(category);
        final Optional<Category> foundCategory = categoryRepository.findByName(data.generateNameWithCustomChars(29));
        Assertions.assertThat(foundCategory.isPresent()).isTrue();
        Assertions.assertThat(foundCategory.get().getName().equals(category.getName()));
    }

    @Test
    public void createCategoryLessThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(10));
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(data.generateNameWithCustomChars(10)));
    }

    @Test
    public void createCategoryWith30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(30));
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(data.generateNameWithCustomChars(30)));
    }

    @Test
    public void createCategoryWith2Chars(){
        Category category = new Category(data.generateNameWithCustomChars(2));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryWith3Chars(){
        Category category = new Category(data.generateNameWithCustomChars(3));
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(data.generateNameWithCustomChars(3)));
    }

    @Test
    public void createCategoryWithExistentName(){
        Category category = new Category("sports");
        DataIntegrityViolationException thrown = org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.saveAndFlush(category));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().contains("could not execute statement"));

    }

    @Test
    public void updateCategoryWithEmptyName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        category.get().setName("");
        ConstraintViolationException thrown = org.junit.jupiter.api.Assertions.assertThrows(ConstraintViolationException.class,
                () -> categoryRepository.saveAndFlush(category.get()));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().contains("category.name_not_empty"));

    }

    @Test
    public void updateCategoryWithBlankName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        category.get().setName("    ");
        ConstraintViolationException thrown = org.junit.jupiter.api.Assertions.assertThrows(ConstraintViolationException.class,
                () -> categoryRepository.saveAndFlush(category.get()));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().contains("category.name_not_blank"));

    }

    @Test
    public void updateCategoryWithNullName(){

        Optional<Category> category = categoryRepository.findByName("sports");
        category.get().setName(null);
        ConstraintViolationException thrown = org.junit.jupiter.api.Assertions.assertThrows(ConstraintViolationException.class,
                () -> categoryRepository.saveAndFlush(category.get()));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().contains("category.name_not_null"));

    }

    @Test
    public void updateCategoryWithExistentName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        category.get().setName("party");
        DataIntegrityViolationException thrown = org.junit.jupiter.api.Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.saveAndFlush(category.get()));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().contains("could not execute statement"));

    }

    @Test
    public void updateCategoryName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        category.get().setName("healthy");
        categoryRepository.saveAndFlush(category.get());
        org.junit.jupiter.api.Assertions.assertTrue(categoryRepository.findByName("healthy").isPresent());
        org.junit.jupiter.api.Assertions.assertFalse(categoryRepository.findByName("sports").isPresent());
    }



    @Test
    public void deleteCategoryByUnexistentId(){
        EmptyResultDataAccessException thrown = org.junit.jupiter.api.Assertions.assertThrows(EmptyResultDataAccessException.class,
                () -> categoryRepository.deleteById(10));
        org.junit.jupiter.api.Assertions.assertTrue(thrown.toString().matches(".*No.*10.*exists.*"));
    }

    @Test
    public void deleteCategoryByUnexistentName(){
        org.junit.jupiter.api.Assertions.assertEquals(0,categoryRepository.deleteByName("UnexistentName"));
    }

    @Test
    public void deleteCategoryByName(){
        org.junit.jupiter.api.Assertions.assertNotEquals(0,categoryRepository.deleteByName("party"));
        org.junit.jupiter.api.Assertions.assertFalse(categoryRepository.findByName("party").isPresent());
    }

    @Test
    public void deleteCategoryById(){
        categoryRepository.deleteById(2);
        Optional<Category> category = categoryRepository.findById(2);
        org.junit.jupiter.api.Assertions.assertFalse(category.isPresent());
    }

    @Test
    public void findCategoryByUnexistentId(){
        Optional<Category> category = categoryRepository.findById(10);
        org.junit.jupiter.api.Assertions.assertTrue(category.isEmpty());
    }

    @Test
    public void findCategoryById(){
        Optional<Category> category = categoryRepository.findById(4);
        org.junit.jupiter.api.Assertions.assertFalse(category.isEmpty());

    }
    @Test
    public void findCategoryByUnexistentName(){
        Optional<Category> category = categoryRepository.findByName("UnexistentName");
        org.junit.jupiter.api.Assertions.assertFalse(category.isPresent());
    }
    @Test
    public void findCategoryByName(){
        Optional<Category> category = categoryRepository.findByName("work");
        org.junit.jupiter.api.Assertions.assertNotNull(category);
    }

}
