package com.andres.course.agy.springboot.cinemamcpserver.app.repositories;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio de catálogo de cine basada en Spring Data JPA y PostgreSQL.
 */
@Repository
public class CinemaCatalogRepositoryImpl implements CinemaCatalogRepository {

    private final MovieRepository movieRepository;

    public CinemaCatalogRepositoryImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getCatalog() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            return movieRepository.findAll();
        }
        return movieRepository.findByTitleContainingIgnoreCase(title.trim());
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            return movieRepository.findAll();
        }
        return movieRepository.findByGenreNameContainingIgnoreCase(genre.trim());
    }

    @Override
    public Optional<Movie> findExactByTitle(String title) {
        if (title == null || title.isBlank()) {
            return Optional.empty();
        }
        return movieRepository.findByTitleIgnoreCase(title.trim());
    }
}
