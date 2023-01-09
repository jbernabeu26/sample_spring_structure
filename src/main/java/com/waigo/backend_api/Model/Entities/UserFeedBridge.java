package com.waigo.backend_api.Model.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

enum Role {
    ADMIN,
    DEFAULT
}

@Entity
public class UserFeedBridge {
    @Id
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "feed_id", referencedColumnName = "id")
    private Feed feed;

    @NotNull(message = "User must have role")
    @Column(name = "role")
    private Role role;

}
