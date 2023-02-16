package com.waigo.backend_api.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Entity
@Validated
public class CustomUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @NotNull(message = "user.null_first_name")
    @NotBlank(message = "user.blank_first_name")
    @Size(min = 2, max = 100, message = "user.first_name_size")
    @Getter
    private String firstName;

    @NotNull(message = "user.null_last_name")
    @NotBlank(message = "user.blank_last_name")
    @Size(min = 2, max = 100, message = "user.last_name_size")
    @Getter
    private String lastName;

    @NotNull(message = "user.null_email")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "user.not_valid_email")
    @Getter
    private String email;

    @NotNull(message = "user.null_description")
    @NotBlank(message = "user.blank_description")
    @Size(min = 100, max = 500, message = "user.description_size")
    @Getter
    private String description;

    @Getter
    private String photo;

    @NotNull(message = "user.null_password")
    @NotBlank(message = "user.blank_password")
    @Getter
    private String password;

    @OneToMany(mappedBy = "owner")
    @Getter
    private Set<Event> events;

    public CustomUser() {
    }


    public CustomUser(String firstName, String lastName, String email, String password, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.photo = "photoTest";
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "id=" + this.id +
                ", firstName=" + this.firstName +
                ", lastName=" + this.lastName +
                ", email=" + this.email +
                ", description=" + this.description +
                ", photo=" + this.photo +
                ", password=" + this.password +
                '}';
    }
}
