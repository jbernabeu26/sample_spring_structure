package com.waigo.backend_api.model.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "event.null_name")
    @Size(min = 2, max = 100, message = "event.nameSize")
    private String name;
    @Nonnull
    @Size(min = 100, max = 500, message = "user.description_size")
    private String description;
    @Nonnull
    @ElementCollection
    private double[] geolocation;
    @Nonnull
    private LocalDateTime startDate;

    private LocalDateTime endDate;

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


    public Event(String name, String description, LocalDateTime startDate, LocalDateTime endDate, PrivacyStatus privacy,
                 Integer maxParticipants, Set<Category> categories, CustomUser owner, double[] geolocation) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.privacy = privacy;
        this.maxParticipants = maxParticipants;
        this.categories = categories;
        this.owner = owner;
        this.geolocation = geolocation;
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


    public LocalDateTime getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDateTime endDate) {
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
