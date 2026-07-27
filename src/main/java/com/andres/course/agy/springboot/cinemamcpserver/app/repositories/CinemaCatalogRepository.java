package com.andres.course.agy.springboot.cinemamcpserver.app.repositories;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz repositorio que define el contrato para consultar la cartelera de cine.
 */
public interface CinemaCatalogRepository {

    /**
     * Obtiene la lista completa de películas en cartelera.
     *
     * @return Lista de películas.
     */
    List<Movie> getCatalog();

    /**
     * Busca películas por coincidencia parcial en el título.
     *
     * @param title Título o fragmento a buscar.
     * @return Lista de películas coincidentes.
     */
    List<Movie> findByTitle(String title);

    /**
     * Busca películas por género.
     *
     * @param genre Género a buscar.
     * @return Lista de películas coincidentes.
     */
    List<Movie> findByGenre(String genre);

    /**
     * Busca una película por su título exacto.
     *
     * @param title Título exacto.
     * @return Optional con la película encontrada si existe.
     */
    Optional<Movie> findExactByTitle(String title);
}
