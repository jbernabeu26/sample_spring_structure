package com.waigo.hexagonal.domain.event;

import com.waigo.hexagonal.domain.category.Category;

import java.util.List;

public interface EventRepository {
    Event save(Event event);

    void deleteById(Long eventId);

    List<Event> findByCategory(Category category);

    Event findById(Long eventId);
}