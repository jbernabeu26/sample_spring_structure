package com.waigo.backend_api.Model.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

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

    private LocalDate endDate;

    @Value("PrivacyStatus.PUBLIC")
    private PrivacyStatus privacy;

    public enum PrivacyStatus {
        PUBLIC,
        PRIVATE,
        MIXED;
    }

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


    @ManyToOne
    @JoinColumn(name = "user_fk")
    private CustomUser owner;


    public Event() {
    }


    public Event(String name, String description, LocalDate startDate, LocalDate endDate, PrivacyStatus privacy,
                 Integer maxParticipants, Set<Category> categories, CustomUser owner) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.privacy = privacy;
        this.maxParticipants = maxParticipants;
        this.categories = categories;
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

    public CustomUser getOwner() {
        return owner;
    }


    public void setOwner(CustomUser owner) {
        this.owner = owner;
    }


}
