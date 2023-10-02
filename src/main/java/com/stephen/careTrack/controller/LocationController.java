package com.stephen.careTrack.controller;

import com.stephen.careTrack.DTO.LocationDTO;
import com.stephen.careTrack.handleExceptions.HCWNotFoundException;
import com.stephen.careTrack.helper.LocationMapper;
import com.stephen.careTrack.model.Location;
import com.stephen.careTrack.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

/**
 * The `LocationController` class handles HTTP requests related to locations.
 * It provides endpoints to retrieve, create, and retrieve specific location details.
 * * @author Ogolla
 *  * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class LocationController
{
    @Autowired
    LocationRepository locationRepository;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/locations")
    List findAll()
    {
        return locationRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/location/{id}")
    ResponseEntity<Location> findById(@PathVariable("id") @Min(1) Long id)
    {
        Location location = locationRepository.findById(id)
                .orElseThrow(()-> new HCWNotFoundException("Location with ID : " + id));
        return ResponseEntity.ok().body(location);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/location")
    ResponseEntity<?> createFacility(@Valid @RequestBody LocationDTO locationDTO)
    {
        Location dtoToEntity = LocationMapper.dtoToEntity(locationDTO);
        Location createLocation = locationRepository.save(dtoToEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createLocation.getId()).toUri();
        return ResponseEntity.created(location).body("Location Created");
    }
}
