package com.waigo.hexagonal.presentation.event;


import com.waigo.hexagonal.application.EventService;
import com.waigo.hexagonal.presentation.category.CategoryDTO;
import com.waigo.hexagonal.presentation.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO event) {
        EventDTO createdEvent = eventService.createEvent(event);
        return ResponseEntity.created(URI.create("/events/" + createdEvent.getId())).body(createdEvent);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO event) {
        event.setId(eventId);
        EventDTO updatedEvent = eventService.updateEvent(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<EventDTO>> getEventsByCategory(@PathVariable Long categoryId) {
        CategoryDTO category = new CategoryDTO();
        category.setId(categoryId);
        List<EventDTO> events = eventService.getEventsByCategory(category);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<EventDTO> addAttendee(@PathVariable Long eventId, @RequestBody UserDTO attendee) {
        EventDTO updatedEvent = eventService.addAttendee(eventId, attendee);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}/attendees")
    public ResponseEntity<EventDTO> removeAttendee(@PathVariable Long eventId, @RequestBody UserDTO attendee) {
        EventDTO updatedEvent = eventService.removeAttendee(eventId, attendee);
        return ResponseEntity.ok(updatedEvent);
    }
}