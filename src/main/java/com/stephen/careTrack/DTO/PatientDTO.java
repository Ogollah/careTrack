package com.stephen.careTrack.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class PatientDTO extends PersonDTO
{
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String patient_number;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPatient_number() {
        return patient_number;
    }

    public void setPatient_number(String patient_number) {
        this.patient_number = patient_number;
    }
}
