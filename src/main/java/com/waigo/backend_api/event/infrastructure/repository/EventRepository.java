package com.waigo.backend_api.event.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waigo.backend_api.event.domain.aggregate.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
