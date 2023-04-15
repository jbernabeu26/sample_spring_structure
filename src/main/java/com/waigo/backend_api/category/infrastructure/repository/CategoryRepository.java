package com.waigo.backend_api.category.infrastructure.repository;

import com.waigo.backend_api.category.domain.entity.Category;
import com.waigo.backend_api.category.domain.port.CategoryRepositoryInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryRepository implements CategoryRepositoryInterface {

    @Autowired
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Optional<Category> findByName(final String name) {
        log.debug("[CategoryRepository] findByName called with name [{}]", name);
        return jpaCategoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAll() {
        log.debug("[CategoryRepository] findAll called");
        return jpaCategoryRepository.findAll();
    }

    @Override
    public Long deleteByName(final String name) {
        log.debug("[CategoryRepository] deleteByName called with name [{}]", name);
        return jpaCategoryRepository.deleteByName(name);
    }

    @Override
    public Category save(Category category) {
        log.debug("[CategoryRepository] save called with category [{}]", category);
        return jpaCategoryRepository.save(category);
    }


}
