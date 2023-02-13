package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

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
        String nameBlank = new String(new char[8]).replace('\0',' ');
        final Category category = new Category(nameBlank);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_not_blank");

    }

    @Test
    public void createCategoryGreaterThan30Chars(){
        String name40Chars = new String(new char[40]).replace('\0','a');
        Category category = new Category(name40Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryGreaterThan30Chars2(){

        String name70Chars = new String(new char[70]).replace('\0','a');
        Category category = new Category(name70Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");
    }

    @Test
    public void createCategoryLessThan30Chars(){
        String name29Chars = new String(new char[29]).replace('\0','a');
        Category category = new Category(name29Chars);
        categoryRepository.saveAndFlush(category);
        final Optional<Category> foundCategory = categoryRepository.findByName(name29Chars);
        Assertions.assertThat(foundCategory.isPresent()).isTrue();
        Assertions.assertThat(foundCategory.get().getName().equals(category.getName()));
    }

    @Test
    public void createCategoryLessThan30Chars2(){
        String name10Chars = new String(new char[10]).replace('\0','a');
        Category category = new Category(name10Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name10Chars));
    }

    @Test
    public void createCategoryWith30Chars(){
        String name30Chars = new String(new char[30]).replace('\0','a');
        Category category = new Category(name30Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name30Chars));
    }

    @Test
    public void createCategoryWith2Chars(){
        String name2Chars = new String(new char[2]).replace('\0','a');
        Category category = new Category(name2Chars);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining("category.name_length_incorrect");

    }

    @Test
    public void createCategoryWith3Chars(){
        String name3Chars = new String(new char[3]).replace('\0','a');
        Category category = new Category(name3Chars);
        categoryRepository.saveAndFlush(category);
        org.junit.jupiter.api.Assertions.assertNotNull(categoryRepository.findByName(name3Chars));
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
