package com.stephen.careTrack.repository;

import com.stephen.careTrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
