package com.waigo.hexagonal.infraestructure.event;

import com.waigo.hexagonal.domain.category.Category;
import com.waigo.hexagonal.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory(Category category);
}