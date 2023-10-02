package com.stephen.careTrack.repository;

import com.stephen.careTrack.model.Location;
import com.stephen.careTrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>
{
    Location findByUser(User user);
}
