package com.waigo.hexagonal.presentation.user;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    @Getter @Setter
    private Long id;
    private String username;
    private String email;

    // getters and setters
}
