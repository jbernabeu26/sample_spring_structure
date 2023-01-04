package com.waigo.backend_api.Entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Set;

@Entity
public class User {
    public User() {

    }

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.photo = user.getPhoto();
        this.password = user.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;
    @NonNull
    @Column(unique = true)
    private String email;
    private String description;
    private String photo;
    @NonNull
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Event> events;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
