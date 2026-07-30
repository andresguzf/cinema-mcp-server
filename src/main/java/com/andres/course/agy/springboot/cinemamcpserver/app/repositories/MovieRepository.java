package com.andres.course.agy.springboot.cinemamcpserver.app.repositories;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    Optional<Movie> findByTitleIgnoreCase(String title);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :genre, '%'))")
    List<Movie> findByGenreNameContainingIgnoreCase(@Param("genre") String genre);
}
