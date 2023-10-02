package com.stephen.careTrack.service;

import com.stephen.careTrack.model.Person;
import com.stephen.careTrack.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person registerPerson(Person person, Person registeredBy) {
        person.setRegisteredBy(registeredBy);
        return personRepository.save(person);
    }
}
