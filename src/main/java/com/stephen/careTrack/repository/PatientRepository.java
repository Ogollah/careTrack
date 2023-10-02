package com.stephen.careTrack.repository;

import com.stephen.careTrack.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientById(Long id);

    @Query("SELECT p FROM Patient p")
    List<Patient> getAllPatientsWithNames();
}
