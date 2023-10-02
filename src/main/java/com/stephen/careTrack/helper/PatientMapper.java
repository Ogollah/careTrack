package com.stephen.careTrack.helper;

import com.stephen.careTrack.DTO.PatientDTO;
import com.stephen.careTrack.model.Patient;

/**
 * The `PatientMapper` class is a utility class responsible for mapping data between the `Patient` entity and the `PatientDTO`
 * (Data Transfer Object) in a Spring Boot application. It provides methods for converting patient entities to DTOs and vice versa.
 *
 * Mapping between these two types is essential for transferring patient data between the client and the server, especially when
 * creating, updating, or retrieving patient records. The class ensures that data is accurately transformed and validated before
 * it is processed by the system.
 *
 * Methods:
 * - `entityToDTO`: Converts a `Patient` entity to a `PatientDTO`.
 * - `dtoToEntity`: Converts a `PatientDTO` to a `Patient` entity.
 *
 * Usage:
 * The `PatientMapper` class is commonly used in conjunction with the `PatientController` to facilitate the conversion of patient
 * data during HTTP requests. It allows for cleaner separation of concerns by keeping the data transformation logic isolated in
 * this utility class.
 *
 * Note that this class does not directly interact with the database but plays a crucial role in ensuring data integrity and
 * consistency in the application.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.PatientDTO
 * @see com.stephen.careTrack.model.Patient
 */
public class PatientMapper extends PersonMapper
{
    public static PatientDTO entityToDTO(Patient patient)
    {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatient_number(patient.getPatient_number());
        return patientDTO;
    }

    public static Patient dtoToEntity(PatientDTO patientDTO)
    {
        Patient patient = new Patient();
        patient.setFirst_name(patientDTO.getFirst_name());
        patient.setLast_name(patientDTO.getLast_name());
        patient.setBirth_date(patientDTO.getBirth_date());
        patient.setIDNumber(patientDTO.getIDNumber());
        patient.setPhone(patientDTO.getPhone());
        patient.setSex(patientDTO.getSex());
        patient.setPatient_number(patientDTO.getPatient_number());
        return patient;
    }
}
