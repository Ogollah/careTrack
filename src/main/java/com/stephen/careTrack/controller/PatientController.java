package com.stephen.careTrack.controller;

import com.stephen.careTrack.DTO.PatientDTO;
import com.stephen.careTrack.handleExceptions.HCWNotFoundException;
import com.stephen.careTrack.helper.PatientMapper;
import com.stephen.careTrack.model.Patient;
import com.stephen.careTrack.model.User;
import com.stephen.careTrack.repository.PatientRepository;
import com.stephen.careTrack.service.PatientService;
import com.stephen.careTrack.service.PersonService;
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
 * The `PatientController` class is a Spring REST controller responsible for managing patient-related operations.
 * It provides endpoints to retrieve, create, update, and delete patient records in the system.
 *
 * This controller enforces role-based access control using Spring Security's `@PreAuthorize` annotation, allowing
 * only users with specific roles and authorities (e.g., 'ADMIN' or 'PROVIDER') to access various operations.
 *
 * The class also handles the validation of incoming patient data and conversion between DTO (Data Transfer Object)
 * and entity objects using the `PatientMapper` class.
 *
 * Endpoints:
 * - GET /api/v1/patients: Retrieve a list of all patients.
 * - GET /api/v1/patient/{id}: Retrieve a patient by their ID.
 * - POST /api/v1/patient: Create a new patient record.
 * - PUT /api/v1/patient/{id}: Update an existing patient record.
 * - DELETE /api/v1/patient/{id}: Delete a patient record by their ID.
 *
 * To use this controller, ensure that the necessary Spring Security configuration, repository interfaces, entity classes,
 * and validation logic are in place. Also, the `HCWNotFoundException` is used to handle cases where a patient is not found
 * by their ID.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.PatientDTO
 * @see com.stephen.careTrack.handleExceptions.HCWNotFoundException
 * @see com.stephen.careTrack.helper.PatientMapper
 * @see com.stephen.careTrack.model.Patient
 * @see com.stephen.careTrack.repository.PatientRepository
 */

@RestController
@RequestMapping("/api/v1")
@Validated
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    // Retrieve all patients
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("/patients")
    List findAll() {
        return patientService.getAllPatientsWithNames();
    }

    // Retrieve a patient by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @GetMapping("/patient/{id}")
    ResponseEntity<Patient> findById(@PathVariable("id") @Min(1) Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Patient with ID : " + id));
        return ResponseEntity.ok().body(patient);
    }

    // Create a new patient
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PostMapping("/patient")
    ResponseEntity<?> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername);
        Patient patient = PatientMapper.dtoToEntity(patientDTO);
        patient.setRegisteredBy(currentUser);
        Patient createPatient = patientRepository.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createPatient.getId()).toUri();
        return ResponseEntity.created(location).body("Success");
    }

    // Update a patient by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @PutMapping("/patient/{id}")
    ResponseEntity<Patient> updatePatient(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody PatientDTO patientDTO) {
        Patient hcw = patientRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Patient with ID :" + id));
        Patient updatePatient = PatientMapper.dtoToEntity(patientDTO);
        updatePatient.setId(hcw.getId());
        patientRepository.save(updatePatient);
        return ResponseEntity.ok().body(updatePatient);
    }

    // Delete a patient by ID
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    @DeleteMapping("/patient/{id}")
    ResponseEntity<String> deletePatient(@PathVariable("id") @Min(1) Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new HCWNotFoundException("Patient with ID :" + id));
        patientRepository.deleteById(patient.getId());
        return ResponseEntity.ok().body("Patient Deleted Successfully");
    }
}
