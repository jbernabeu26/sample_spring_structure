package com.waigo.backend_api.repository;

import com.waigo.backend_api.Model.Entities.Category;
import com.waigo.backend_api.Model.Repositories.CategoryRepository;
import com.waigo.backend_api.Services.CategoryService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CategoryRepositoryTest
{
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryService categoryService;

    private Category category;
    @BeforeAll
    void setUp(){
        category = new Category("sports");
    }

    @Test void addOne(){
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category newCategory = categoryService.addCategory(new Category("sports"));
        Assertions.assertEquals(category.getName(), newCategory.getName());
    }

}
