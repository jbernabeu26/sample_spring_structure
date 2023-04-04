package com.waigo.hexagonal.domain.event;



import com.waigo.hexagonal.domain.category.Category;
import com.waigo.hexagonal.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    @Getter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private LocalDateTime date;
    @Getter @Setter private Category category;
    @Getter @Setter private List<User> attendees;

}