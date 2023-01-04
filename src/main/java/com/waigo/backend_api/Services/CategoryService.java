package com.waigo.backend_api.Services;

import com.waigo.backend_api.Entities.Category;
import com.waigo.backend_api.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements CategoryRepository {
    @Override
    public <S extends Category> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
