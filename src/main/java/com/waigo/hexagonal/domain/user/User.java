package com.waigo.hexagonal.domain.user;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter private String email;
    // getters and setters
}
