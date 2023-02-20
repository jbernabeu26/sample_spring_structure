package com.waigo.backend_api.category.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.category.domain.aggregate.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);
    List<Category> findAll();
    Long deleteByName(String name);

}
