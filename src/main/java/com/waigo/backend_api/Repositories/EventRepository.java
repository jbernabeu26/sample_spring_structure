package com.waigo.backend_api.Repositories;

import com.waigo.backend_api.Entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
