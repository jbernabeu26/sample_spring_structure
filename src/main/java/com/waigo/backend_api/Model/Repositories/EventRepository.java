package com.waigo.backend_api.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.Model.Entities.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
