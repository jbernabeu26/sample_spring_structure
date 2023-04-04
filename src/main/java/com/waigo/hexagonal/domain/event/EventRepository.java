package com.waigo.hexagonal.domain.event;

public interface EventRepository {
    Event save(Event event);

    void deleteById(Long eventId);

    List<Event> findByCategory(Category category);

    Event findById(Long eventId);
}