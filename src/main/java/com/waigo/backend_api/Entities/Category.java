package com.waigo.backend_api.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "categories")
    private Set<Event> events = new HashSet<>();


    public Category(){
        this.name = "NoName";
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
