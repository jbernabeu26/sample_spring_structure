package com.waigo.backend_api.event.domain.aggregate;

import com.waigo.backend_api.category.domain.aggregate.Category;
import com.waigo.backend_api.user.domain.aggregate.CustomUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
public class Event {

    private Integer id;

    private String name;

    private String description;

    private List<String> geolocation;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    private int privacy;


    private Integer maxParticipants;


    private List<String> photos;


    private Set<Category> categories;

    private CustomUser owner;


}
