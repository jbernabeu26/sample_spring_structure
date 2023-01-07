package com.waigo.backend_api.Model.Entities;

import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String name;


    @ManyToMany(cascade = {
        CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "locations")
    private Set<Event> events = new HashSet<>();


    public Location(){}
    public Location(String name) {
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
