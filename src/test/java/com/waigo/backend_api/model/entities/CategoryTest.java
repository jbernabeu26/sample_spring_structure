package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.CategoryRepository;
import com.waigo.backend_api.utils.Constants;
import com.waigo.backend_api.utils.MockDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
                .hasMessageContaining(Constants.CATEGORY_NAME_NULL);
    }

    @Test
    public void createCategoryWithWrongName(){
        final Category category = new Category(null);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_NULL);

    }
    @Test
    public void createCategoryWithWrongName2(){

        final Category category = new Category("");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_EMPTY);
    }

    @Test
    public void createCategoryWithWrongName3(){
        var blank = ' ';
        final Category category = new Category(data.generateNameWithCustomChars(8, blank));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_BLANK);

    }

    @Test
    public void createCategoryGreaterThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(40));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);

    }

    @Test
    public void createCategoryGreaterThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(70));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);
    }

    @Test
    public void createCategoryLessThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(29));
        categoryRepository.saveAndFlush(category);
        final Optional<Category> foundCategory = categoryRepository.findByName(data.generateNameWithCustomChars(29));
        Assertions.assertThat(foundCategory.isPresent()).isTrue();
        Assertions.assertThat(foundCategory.get().getName()).isEqualTo(category.getName());
    }

    @Test
    public void createCategoryLessThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(10));
        categoryRepository.saveAndFlush(category);
        Assertions.assertThat(categoryRepository.findByName(data.generateNameWithCustomChars(10)).isPresent()).isTrue();
    }

    @Test
    public void createCategoryWith30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(30));
        categoryRepository.saveAndFlush(category);
        Assertions.assertThat(categoryRepository.findByName(data.generateNameWithCustomChars(30))).isNotNull();
    }

    @Test
    public void createCategoryWith2Chars(){
        Category category = new Category(data.generateNameWithCustomChars(2));
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);

    }

    @Test
    public void createCategoryWith3Chars(){
        Category category = new Category(data.generateNameWithCustomChars(3));
        categoryRepository.saveAndFlush(category);
        Assertions.assertThat(categoryRepository.findByName(data.generateNameWithCustomChars(3))).isNotNull();
    }

    @Test
    public void createCategoryWithExistentName(){
        Category category = new Category("sports");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category)).hasMessageContaining("could not execute statement");

    }

    @Test
    public void updateCategoryWithEmptyName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_EMPTY);


    }

    @Test
    public void updateCategoryWithBlankName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("    ");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_BLANK);

    }

    @Test
    public void updateCategoryWithNullName(){

        Optional<Category> category = categoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName(null);
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_NULL);
    }

    @Test
    public void updateCategoryWithExistentName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("party");
        Assertions.assertThatThrownBy(() -> categoryRepository.saveAndFlush(category.get())).hasMessageContaining("could not execute statement");

    }

    @Test
    public void updateCategoryName(){
        Optional<Category> category = categoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("healthy");
        categoryRepository.saveAndFlush(category.get());
        Assertions.assertThat(categoryRepository.findByName("healthy").isPresent()).isTrue();
        Assertions.assertThat(categoryRepository.findByName("sports").isPresent()).isFalse();
    }



    @Test
    public void deleteCategoryByUnexistentId(){
        Assertions.assertThatThrownBy(() -> categoryRepository.deleteById(10)).hasMessageFindingMatch(".*No.*10.*exists.*");
    }

    @Test
    public void deleteCategoryByUnexistentName(){
        Assertions.assertThat(categoryRepository.deleteByName("UnexistentName")).isEqualTo(0);
    }

    @Test
    public void deleteCategoryByName(){
        Assertions.assertThat(categoryRepository.deleteByName("party")).isNotEqualTo(0);
        Assertions.assertThat(categoryRepository.findByName("party").isPresent()).isFalse();
    }

    @Test
    public void deleteCategoryById(){
        categoryRepository.deleteById(2);
        Optional<Category> category = categoryRepository.findById(2);
        Assertions.assertThat(category.isPresent()).isFalse();
    }

    @Test
    public void findCategoryByUnexistentId(){
        Optional<Category> category = categoryRepository.findById(10);
        Assertions.assertThat(category.isEmpty()).isTrue();
    }

    @Test
    public void findCategoryById(){
        Optional<Category> category = categoryRepository.findById(4);
        Assertions.assertThat(category.isEmpty()).isFalse();

    }
    @Test
    public void findCategoryByUnexistentName(){
        Optional<Category> category = categoryRepository.findByName("UnexistentName");
        Assertions.assertThat(category.isPresent()).isFalse();
    }
    @Test
    public void findCategoryByName(){
        Optional<Category> category = categoryRepository.findByName("work");
        Assertions.assertThat(category).isNotNull();
    }

}
