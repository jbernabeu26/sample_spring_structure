package com.waigo.backend_api.category.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Category {

    private Integer id;
    private String name;

}
