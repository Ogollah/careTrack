package com.stephen.careTrack.service;

import com.stephen.careTrack.model.Patient;
import com.stephen.careTrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(Long id)
    {
        return patientRepository.findPatientById(id);
    }

    public List<Patient> getAllPatientsWithNames() {
        return patientRepository.getAllPatientsWithNames();
    }
}
