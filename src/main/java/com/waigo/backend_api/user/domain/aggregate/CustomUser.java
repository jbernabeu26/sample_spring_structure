package com.waigo.backend_api.user.domain.aggregate;

import com.waigo.backend_api.common.utils.Constants;
import com.waigo.backend_api.event.domain.aggregate.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Builder
@Getter
@Setter
public class CustomUser {


    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String description;

    private String photo;

    private String password;

    private Set<Event> events;

}
