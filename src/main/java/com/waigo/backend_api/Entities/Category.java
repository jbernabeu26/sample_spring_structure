package com.waigo.backend_api.Entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    public Category() {
    }

    public Category(@NonNull String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "categories")
    private Set<Event> events = new HashSet<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {

        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
