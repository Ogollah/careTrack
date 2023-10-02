package com.stephen.careTrack.repository;

import com.stephen.careTrack.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
