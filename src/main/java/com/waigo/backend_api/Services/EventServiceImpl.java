package com.waigo.backend_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventRepository eventRepository;
    
}
