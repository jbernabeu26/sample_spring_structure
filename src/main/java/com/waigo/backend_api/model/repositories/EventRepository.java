package com.waigo.backend_api.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.model.entities.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
