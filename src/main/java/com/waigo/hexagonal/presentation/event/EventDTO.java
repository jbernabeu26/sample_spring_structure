package com.waigo.hexagonal.presentation.event;

import com.waigo.hexagonal.presentation.category.CategoryDTO;
import com.waigo.hexagonal.presentation.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class EventDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private LocalDate date;
    @Getter @Setter private List<CategoryDTO> categories;
    @Getter @Setter private List<UserDTO> attendees;

    // getters and setters
}
