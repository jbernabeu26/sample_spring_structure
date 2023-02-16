package com.waigo.backend_api.model.entities;


import com.waigo.backend_api.utils.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = Constants.CATEGORY_NAME_NULL)
    @NotEmpty(message = Constants.CATEGORY_NAME_EMPTY)
    @NotBlank(message = Constants.CATEGORY_NAME_BLANK)
    @Size(min = Constants.CATEGORY_NAME_SIZE_MIN, max = Constants.CATEGORY_NAME_SIZE_MAX, message = Constants.CATEGORY_NAME_SIZE)
    @Column(unique = true)
    @Getter
    @Setter
    private String name;

    @ManyToMany(cascade = {
            CascadeType.DETACH
    }, fetch = FetchType.LAZY,
            mappedBy = "categories")
    private Set<Event> events = new HashSet<>();


    public Category() {
    }

    public Category(@NonNull String name) {
        this.name = name;
    }


}
