package com.waigo.backend_api.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class CustomUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    @NotNull(message = "user.name_not_null")
    private String email;

    private String description;
    private String photo;

    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Event> events;


    public CustomUser() {
    }

    public CustomUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
