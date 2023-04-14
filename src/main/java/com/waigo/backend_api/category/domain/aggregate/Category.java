package com.waigo.backend_api.category.domain.aggregate;


import com.waigo.backend_api.event.domain.aggregate.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class Category {

    private Integer id;
    private String name;

    private Set<Event> events;


}
