package com.waigo.backend_api.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "Category_Event",
            joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")}
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User owner;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
