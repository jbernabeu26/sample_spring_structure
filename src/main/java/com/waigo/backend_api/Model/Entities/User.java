package com.waigo.backend_api.Model.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    private String firstName;

    private String lastName;

    @Nonnull
    private String email;

    private String description;
    private String photo;

    @Nonnull
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Event> events;

    @OneToMany(mappedBy = "user")
    private Set<UserFeedBridge> userFeedBridge;


    @OneToMany(mappedBy = "user")
    private Set<Message> messages;


    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

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

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<UserFeedBridge> getFeeds() {
        return userFeedBridge;
    }

}
