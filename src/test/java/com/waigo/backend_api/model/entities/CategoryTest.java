package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.category.infrastructure.repository.JpaCategoryRepository;
import com.waigo.backend_api.common.utils.Constants;
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
    private JpaCategoryRepository jpaCategoryRepository;
    private final MockDataGenerator data = new MockDataGenerator();

    @BeforeAll
    public void setUp(){
        jpaCategoryRepository.saveAndFlush(new Category("sports"));
        jpaCategoryRepository.saveAndFlush(new Category("study"));
        jpaCategoryRepository.saveAndFlush(new Category("party"));
        jpaCategoryRepository.saveAndFlush(new Category("work"));
        jpaCategoryRepository.saveAndFlush(new Category("business"));

    }

    @Test
    public void createCategoryWithoutData(){
        final Category category = new Category();
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_NULL);
    }

    @Test
    public void createCategoryWithWrongName(){
        final Category category = new Category(null);
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_NULL);

    }
    @Test
    public void createCategoryWithWrongName2(){

        final Category category = new Category("");
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_EMPTY);
    }

    @Test
    public void createCategoryWithWrongName3(){
        var blank = ' ';
        final Category category = new Category(data.generateNameWithCustomChars(8, blank));
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_BLANK);

    }

    @Test
    public void createCategoryGreaterThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(40));
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);

    }

    @Test
    public void createCategoryGreaterThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(70));
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);
    }

    @Test
    public void createCategoryLessThan30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(29));
        jpaCategoryRepository.saveAndFlush(category);
        final Optional<Category> foundCategory = jpaCategoryRepository.findByName(data.generateNameWithCustomChars(29));
        Assertions.assertThat(foundCategory.isPresent()).isTrue();
        Assertions.assertThat(foundCategory.get().getName()).isEqualTo(category.getName());
    }

    @Test
    public void createCategoryLessThan30Chars2(){
        Category category = new Category(data.generateNameWithCustomChars(10));
        jpaCategoryRepository.saveAndFlush(category);
        Assertions.assertThat(jpaCategoryRepository.findByName(data.generateNameWithCustomChars(10)).isPresent()).isTrue();
    }

    @Test
    public void createCategoryWith30Chars(){
        Category category = new Category(data.generateNameWithCustomChars(30));
        jpaCategoryRepository.saveAndFlush(category);
        Assertions.assertThat(jpaCategoryRepository.findByName(data.generateNameWithCustomChars(30))).isNotNull();
    }

    @Test
    public void createCategoryWith2Chars(){
        Category category = new Category(data.generateNameWithCustomChars(2));
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category))
                .hasMessageContaining(Constants.CATEGORY_NAME_SIZE);

    }

    @Test
    public void createCategoryWith3Chars(){
        Category category = new Category(data.generateNameWithCustomChars(3));
        jpaCategoryRepository.saveAndFlush(category);
        Assertions.assertThat(jpaCategoryRepository.findByName(data.generateNameWithCustomChars(3))).isNotNull();
    }

    @Test
    public void createCategoryWithExistentName(){
        Category category = new Category("sports");
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category)).hasMessageContaining("could not execute statement");

    }

    @Test
    public void updateCategoryWithEmptyName(){
        Optional<Category> category = jpaCategoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("");
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_EMPTY);


    }

    @Test
    public void updateCategoryWithBlankName(){
        Optional<Category> category = jpaCategoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("    ");
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_BLANK);

    }

    @Test
    public void updateCategoryWithNullName(){

        Optional<Category> category = jpaCategoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName(null);
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category.get())).hasMessageContaining(Constants.CATEGORY_NAME_NULL);
    }

    @Test
    public void updateCategoryWithExistentName(){
        Optional<Category> category = jpaCategoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("party");
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.saveAndFlush(category.get())).hasMessageContaining("could not execute statement");

    }

    @Test
    public void updateCategoryName(){
        Optional<Category> category = jpaCategoryRepository.findByName("sports");
        Assertions.assertThat(category.isPresent()).isTrue();
        category.get().setName("healthy");
        jpaCategoryRepository.saveAndFlush(category.get());
        Assertions.assertThat(jpaCategoryRepository.findByName("healthy").isPresent()).isTrue();
        Assertions.assertThat(jpaCategoryRepository.findByName("sports").isPresent()).isFalse();
    }



    @Test
    public void deleteCategoryByUnexistentId(){
        Assertions.assertThatThrownBy(() -> jpaCategoryRepository.deleteById(10)).hasMessageFindingMatch(".*No.*10.*exists.*");
    }

    @Test
    public void deleteCategoryByUnexistentName(){
        Assertions.assertThat(jpaCategoryRepository.deleteByName("UnexistentName")).isEqualTo(0);
    }

    @Test
    public void deleteCategoryByName(){
        Assertions.assertThat(jpaCategoryRepository.deleteByName("party")).isNotEqualTo(0);
        Assertions.assertThat(jpaCategoryRepository.findByName("party").isPresent()).isFalse();
    }

    @Test
    public void deleteCategoryById(){
        jpaCategoryRepository.deleteById(2);
        Optional<Category> category = jpaCategoryRepository.findById(2);
        Assertions.assertThat(category.isPresent()).isFalse();
    }

    @Test
    public void findCategoryByUnexistentId(){
        Optional<Category> category = jpaCategoryRepository.findById(10);
        Assertions.assertThat(category.isEmpty()).isTrue();
    }

    @Test
    public void findCategoryById(){
        Optional<Category> category = jpaCategoryRepository.findById(4);
        Assertions.assertThat(category.isEmpty()).isFalse();

    }
    @Test
    public void findCategoryByUnexistentName(){
        Optional<Category> category = jpaCategoryRepository.findByName("UnexistentName");
        Assertions.assertThat(category.isPresent()).isFalse();
    }
    @Test
    public void findCategoryByName(){
        Optional<Category> category = jpaCategoryRepository.findByName("work");
        Assertions.assertThat(category).isNotNull();
    }

}
