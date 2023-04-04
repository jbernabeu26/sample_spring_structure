package com.waigo.hexagonal.application;

import com.waigo.hexagonal.domain.category.Category;
import com.waigo.hexagonal.domain.event.Event;
import com.waigo.hexagonal.domain.event.EventRepository;
import com.waigo.hexagonal.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public EventServiceImpl(EventRepository eventRepository, CategoryService categoryService, UserService userService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public Event createEvent(Event event) {
        Category category = categoryService.getCategoryById(event.getCategory().getId());
        event.setCategory(category);
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        Category category = categoryService.getCategoryById(event.getCategory().getId());
        event.setCategory(category);
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getEventsByCategory(Category category) {
        return eventRepository.findByCategory(category);
    }

    @Override
    public Event addAttendee(Long eventId, User attendee) {
        Event event = getEventById(eventId);
        User user = userService.getUserById(attendee.getId());
        event.addAttendee(user);
        return eventRepository.save(event);
    }

    @Override
    public Event removeAttendee(Long eventId, User attendee) {
        Event event = getEventById(eventId);
        User user = userService.getUserById(attendee.getId());
        event.removeAttendee(user);
        return eventRepository.save(event);
    }

    private Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }
}