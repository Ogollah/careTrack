package com.stephen.careTrack.controller;

import com.stephen.careTrack.DTO.VisitDTO;
import com.stephen.careTrack.handleExceptions.HCWNotFoundException;
import com.stephen.careTrack.helper.VisitMapper;
import com.stephen.careTrack.model.Patient;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.model.Visit;
import com.stephen.careTrack.repository.VisitRepository;
import com.stephen.careTrack.service.PatientService;
import com.stephen.careTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

/**
 * The `VisitController` class is a Spring REST controller responsible for managing visit-related operations.
 * It provides endpoints to retrieve, create, update, and delete visit records in the system.
 *
 * This controller enforces role-based access control using Spring Security's `@PreAuthorize` annotation, allowing
 * only users with specific roles and authorities (e.g., 'ADMIN' or 'PROVIDER') to access various operations.
 *
 * The class also handles the validation of incoming visit data and conversion between DTO (Data Transfer Object)
 * and entity objects using the `VisitMapper` class.
 *
 * Endpoints:
 * - GET /api/v1/visits: Retrieve a list of all visits.
 * - GET /api/v1/visit/{id}: Retrieve a visit by its ID.
 * - POST /api/v1/visit/{id}: Create a new visit record.
 * - PUT /api/v1/visit/{id}: Update an existing visit record.
 * - DELETE /api/v1/visit/{id}: Delete a visit record by its ID.
 *
 * To use this controller, ensure that the necessary Spring Security configuration, repository interfaces, entity classes,
 * and validation logic are in place. Also, the `HCWNotFoundException` is used to handle cases where a visit is not found
 * by its ID.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.VisitDTO
 * @see com.stephen.careTrack.handleExceptions.HCWNotFoundException
 * @see com.stephen.careTrack.helper.VisitMapper
 * @see com.stephen.careTrack.model.Visit
 * @see com.stephen.careTrack.repository.VisitRepository
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class VisitController {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    // Retrieve all Visits
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("/visits")
    List findAll() {
        return visitRepository.findAll();
    }

    // Retrieve a visit by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("/visit/{id}")
    ResponseEntity<Visit> findById(@PathVariable("id") @Min(1) Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Visit with ID : " + id));
        return ResponseEntity.ok().body(visit);
    }

    // Create a new visit
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PostMapping("/visit/{id}")
    ResponseEntity<?> createVisit(@Valid @RequestBody VisitDTO visitDTO, @PathVariable("id") @Min(1) Long id) {
        Patient patient = patientService.getPatientById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername);
        Visit visit = VisitMapper.dtoToEntity(visitDTO);
        visit.setRegisteredBy(currentUser);
        visit.setPatient(patient); // Set the Patient for the Visit
        Visit createVisit = visitRepository.save(visit);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createVisit.getId()).toUri();
        return ResponseEntity.created(location).body("Visit Added");
    }

    // Update a Visit by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PutMapping("/visit/{id}")
    ResponseEntity<Visit> updateVisit(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody VisitDTO visitDTO) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Visit with ID :" + id));
        Visit updateVisit = VisitMapper.dtoToEntity(visitDTO);
        updateVisit.setId(visit.getId());
        visitRepository.save(updateVisit);
        return ResponseEntity.ok().body(updateVisit);
    }

    // Delete a visit by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @DeleteMapping("/visit/{id}")
    ResponseEntity<String> deleteVisit(@PathVariable("id") @Min(1) Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Patient with ID :" + id));
        visitRepository.deleteById(visit.getId());
        return ResponseEntity.ok().body("Visit Deleted Successfully");
    }
}
