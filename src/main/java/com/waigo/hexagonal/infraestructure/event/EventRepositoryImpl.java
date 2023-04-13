package com.waigo.hexagonal.infraestructure.event;

import com.waigo.hexagonal.domain.category.Category;
import com.waigo.hexagonal.domain.event.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import com.waigo.hexagonal.domain.event.Event;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private final JpaEventRepository jpaEventRepository;

    public EventRepositoryImpl(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        return jpaEventRepository.save(event);
    }

    @Override
    public void deleteById(Long eventId) {
        jpaEventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> findByCategory(Category category) {
        return jpaEventRepository.findByCategory(category);
    }

    @Override
    public Event findById(Long eventId) {
        return jpaEventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }
}