package com.waigo.hexagonal.presentation.category;

import lombok.Getter;
import lombok.Setter;

public class CategoryDTO {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    public CategoryDTO(){}
    // getters and setters
}