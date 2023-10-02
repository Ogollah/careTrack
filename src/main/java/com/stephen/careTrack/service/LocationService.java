package com.stephen.careTrack.service;

import com.stephen.careTrack.model.Location;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository; // Create a LocationRepository interface

    public Location getLocationByUser(User user) {
        return locationRepository.findByUser(user);
    }
}
