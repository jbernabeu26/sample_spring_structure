package com.waigo.hexagonal.infraestructure.category;

import com.waigo.hexagonal.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
