package com.waigo.backend_api.model.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.waigo.backend_api.utils.Constants.*;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "event.null_name")
    @Size(min = 2, max = 5, message = "event.name_size")
    @NotBlank(message="event.blank_name")
    private String name;
    @NotNull(message = "event.null_description")
    @Size(min = EVENT_DESCRIPTION_MIN, max = EVENT_DESCRIPTION_MAX, message = "event.description_size")
    private String description;
    @Nonnull
    @ElementCollection
    private List<String> geolocation;
    @NotNull(message = "event.null_startDate")
    private LocalDateTime startDate;
    @NotNull(message = "event.null_endDate")
    private LocalDateTime endDate;
    @NotNull(message = "event.null_privacy")
    @Value("PrivacyStatus.PUBLIC")
    private PrivacyStatus privacy;

    public enum PrivacyStatus {
        PUBLIC,
        PRIVATE,
        MIXED;
    }
    @Min(EVENT_PARTICIPANTS_MIN)
    @Max(EVENT_PARTICIPANTS_MAX)
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
                 Integer maxParticipants, Set<Category> categories, CustomUser owner, List<String> geolocation) {
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


    public List<String> getGeolocation() {
        return geolocation;
    }


    public void setGeolocation(List<String> geolocation) {
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
