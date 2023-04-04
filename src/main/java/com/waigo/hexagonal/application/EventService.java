package com.waigo.hexagonal.application;

import com.waigo.hexagonal.presentation.category.CategoryDTO;
import com.waigo.hexagonal.presentation.event.EventDTO;
import com.waigo.hexagonal.presentation.user.UserDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO event);
    EventDTO updateEvent(EventDTO event);
    void deleteEvent(Long eventId);
    List<EventDTO> getEventsByCategory(CategoryDTO category);
    EventDTO addAttendee(Long eventId, UserDTO attendee);
    EventDTO removeAttendee(Long eventId, UserDTO attendee);
}