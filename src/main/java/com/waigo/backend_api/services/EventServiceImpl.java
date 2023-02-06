package com.waigo.backend_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.model.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventRepository eventRepository;

}
