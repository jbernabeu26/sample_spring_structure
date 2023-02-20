package com.waigo.backend_api.category.application.dto;

import com.waigo.backend_api.event.domain.aggregate.Event;
import lombok.Builder;

import java.util.Set;

@Builder(toBuilder = true)
public record AddCategoryRequest(String name,
                                 Integer id,
                                 Set<Event> events) {
}
