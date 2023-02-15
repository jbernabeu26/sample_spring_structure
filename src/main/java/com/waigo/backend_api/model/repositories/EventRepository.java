package com.waigo.backend_api.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.model.entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
