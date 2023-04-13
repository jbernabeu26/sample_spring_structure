package com.waigo.hexagonal.infraestructure.event;



import com.waigo.hexagonal.infraestructure.category.Category;
import com.waigo.hexagonal.infraestructure.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Category category;
    private List<User> attendees;
    // getters and setters
}