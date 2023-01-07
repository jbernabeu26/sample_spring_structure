package com.waigo.backend_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService{
    
    @Autowired
    LocationRepository locationRepository;
}
