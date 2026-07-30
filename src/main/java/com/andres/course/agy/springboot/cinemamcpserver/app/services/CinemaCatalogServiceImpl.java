package com.andres.course.agy.springboot.cinemamcpserver.app.services;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.CinemaCatalogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la capa de servicio del catálogo de cine.
 */
@Service
@Transactional(readOnly = true)
public class CinemaCatalogServiceImpl implements CinemaCatalogService {

    private final CinemaCatalogRepository cinemaCatalogRepository;

    public CinemaCatalogServiceImpl(CinemaCatalogRepository cinemaCatalogRepository) {
        this.cinemaCatalogRepository = cinemaCatalogRepository;
    }

    @Override
    public List<Movie> getCatalog() {
        return cinemaCatalogRepository.getCatalog();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return cinemaCatalogRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return cinemaCatalogRepository.findByGenre(genre);
    }

    @Override
    public Optional<Movie> findExactByTitle(String title) {
        return cinemaCatalogRepository.findExactByTitle(title);
    }
}
