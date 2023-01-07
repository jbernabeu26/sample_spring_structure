package com.waigo.backend_api.Model.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    private String name;

    @ManyToOne
    @JoinColumn(name = "event_fk")
    private Event event;

    @ManyToMany(cascade = {
        CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "feeds")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "feed")
    private  Set<Message> messages;



    @Nonnull
    private boolean isGeneral;

    private String description;

    public Feed() {
    }

    public Feed(String name, Event event, boolean isGeneral) {
        this.name = name;
        this.event = event;
        this.isGeneral = isGeneral;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean isGeneral) {
        this.isGeneral = isGeneral;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    

 
    

    

}