package com.waigo.backend_api.Model.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Nonnull
    private String name;
    @Nonnull
    private String description;
    @Nonnull
    @ElementCollection
    private double[] geolocation;
    @Nonnull
    private LocalDate startDate;
    @Nonnull
    private LocalDate endDate;

    @Nonnull
    private PrivacyStatus privacy;

    public enum PrivacyStatus {
        PUBLIC,
        PRIVATE,
        MIXED;
    }

    @Nonnull
    private Integer maxParticipants;

    @ElementCollection
    private List<String> photos;


    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "Category_Event",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "Interest_Event",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id", referencedColumnName = "id")}
    )
    private Set<Interest> interests = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "Location_Event",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id", referencedColumnName = "id")}
    )
    private Set<Location> locations = new HashSet<>();


    @OneToMany(mappedBy = "event")
    private Set<Feed> feeds;


    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User owner;


    public Event() {
    }


    public Event(String name, String description, LocalDate startDate, LocalDate endDate, PrivacyStatus privacy,
                 Integer maxParticipants, Set<Category> categories, Set<Interest> interests, Set<Location> locations,
                 Set<Feed> feeds, User owner) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.privacy = privacy;
        this.maxParticipants = maxParticipants;
        this.categories = categories;
        this.interests = interests;
        this.locations = locations;
        this.feeds = feeds;
        this.owner = owner;
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


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public double[] getGeolocation() {
        return geolocation;
    }


    public void setGeolocation(double[] geolocation) {
        this.geolocation = geolocation;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public PrivacyStatus getPrivacity() {
        return privacy;
    }


    public void setPrivacity(PrivacyStatus privacy) {
        this.privacy = privacy;
    }


    public Integer getMaxParticipants() {
        return maxParticipants;
    }


    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }


    public List<String> getPhotos() {
        return photos;
    }


    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }


    public Set<Category> getCategories() {
        return categories;
    }


    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    public Set<Interest> getInterests() {
        return interests;
    }


    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }


    public Set<Location> getLocations() {
        return locations;
    }


    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }


    public Set<Feed> getFeeds() {
        return feeds;
    }


    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }


    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }


}
