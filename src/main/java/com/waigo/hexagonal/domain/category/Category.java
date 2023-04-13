package com.waigo.hexagonal.domain.category;

import com.waigo.hexagonal.domain.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class Category {
    @Getter @Setter
    private Long id;

    @NotEmpty(message = Constants.CATEGORY_NAME_EMPTY)
    @NotBlank(message = Constants.CATEGORY_NAME_BLANK)
    @Size(min = Constants.CATEGORY_NAME_SIZE_MIN, max = Constants.CATEGORY_NAME_SIZE_MAX, message = Constants.CATEGORY_NAME_SIZE)
    @Getter @Setter private String name;
    // getters and setters
}