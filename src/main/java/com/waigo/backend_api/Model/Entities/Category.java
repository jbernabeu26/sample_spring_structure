package com.waigo.backend_api.Model.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "category.name_not_null")
    @NotEmpty(message = "category.name_not_empty")
    @NotBlank(message = "category.name_not_blank")
    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "categories")
    private Set<Event> events = new HashSet<>();


    public Category() {
    }

    public Category(@NonNull String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


}
