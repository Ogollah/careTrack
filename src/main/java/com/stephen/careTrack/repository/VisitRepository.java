package com.stephen.careTrack.repository;

import com.stephen.careTrack.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long>
{
}
