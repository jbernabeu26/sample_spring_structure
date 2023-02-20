package com.waigo.backend_api.user.domain.aggregate;

import com.waigo.backend_api.common.utils.Constants;
import com.waigo.backend_api.event.domain.aggregate.Event;
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

    @NotNull(message = Constants.CUSTOM_USER_FIRST_NANE_NULL)
    @NotBlank(message = Constants.CUSTOM_USER_FIRST_NANE_BLANK)
    @Size(min = Constants.CUSTOM_USER_FIRST_NAME_MIN, max = Constants.CUSTOM_USER_FIRST_NAME_MAX, message = Constants.CUSTOM_USER_FIRST_NANE_SIZE)
    @Getter
    private String firstName;

    @NotNull(message = Constants.CUSTOM_USER_LAST_NAME_NULL)
    @NotBlank(message = Constants.CUSTOM_USER_LAST_NAME_BLANK)
    @Size(min = Constants.CUSTOM_USER_LAST_NAME_MIN, max = Constants.CUSTOM_USER_LAST_NAME_MAX, message = Constants.CUSTOM_USER_LAST_NAME_SIZE)
    @Getter
    private String lastName;

    @NotNull(message = Constants.CUSTOM_USER_EMAIL_NULL)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = Constants.CUSTOM_USER_EMAIL_VALID)
    @Getter
    private String email;

    @Size(max = Constants.CUSTOM_USER_DESCRIPTION_MAX, message = Constants.CUSTOM_USER_DESCRIPTION_SIZE)
    @Getter
    private String description;

    @Getter
    private String photo;

    @NotNull(message = Constants.CUSTOM_USER_PASSWORD_NULL)
    @NotBlank(message = Constants.CUSTOM_USER_PASSWORD_BLANK)
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
