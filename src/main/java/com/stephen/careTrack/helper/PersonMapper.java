package com.stephen.careTrack.helper;

import com.stephen.careTrack.DTO.PersonDTO;
import com.stephen.careTrack.model.Person;
import org.springframework.stereotype.Service;

/**
 * The `PersonMapper` class is responsible for mapping between Person and PersonDTO objects.
 * It provides static methods to convert Person objects to PersonDTOs and vice versa.
 *
 * This mapping class is crucial for transferring person-related data between the data access layer and the presentation layer.
 *
 * @author Ogolla
 * @version 1.0
 * @see com.stephen.careTrack.DTO.PersonDTO
 * @see com.stephen.careTrack.model.Person
 */
@Service
public class PersonMapper {
    public static Person dtoToEntity(PersonDTO personDTO)
    {
        Person person = new Person();
        person.setFirst_name(personDTO.getFirst_name());
        person.setLast_name(personDTO.getLast_name());
        person.setBirth_date(personDTO.getBirth_date());
        person.setIDNumber(personDTO.getIDNumber());
        person.setPhone(personDTO.getPhone());
        person.setSex(personDTO.getSex());
        return person;
    }

    public static PersonDTO entityToDTO(Person patient)
    {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirst_name(patient.getFirst_name());
        personDTO.setLast_name(patient.getLast_name());
        personDTO.setBirth_date(patient.getBirth_date());
        personDTO.setIDNumber(patient.getIDNumber());
        personDTO.setPhone(patient.getPhone());
        personDTO.setSex(patient.getSex());
        return personDTO;
    }
}
