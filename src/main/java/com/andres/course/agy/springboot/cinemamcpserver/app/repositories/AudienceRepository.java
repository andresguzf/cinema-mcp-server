package com.andres.course.agy.springboot.cinemamcpserver.app.repositories;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Audience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AudienceRepository extends JpaRepository<Audience, Long> {
    Optional<Audience> findByName(String name);
}
