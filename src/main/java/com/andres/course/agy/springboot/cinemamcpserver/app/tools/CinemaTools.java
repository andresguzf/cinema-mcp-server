package com.andres.course.agy.springboot.cinemamcpserver.app.tools;

import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieAudienceDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieScheduleDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieSummaryDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import com.andres.course.agy.springboot.cinemamcpserver.app.services.CinemaCatalogService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Herramientas MCP para consultar información del cine a través de la capa de servicio.
 */
@Component
public class CinemaTools {

    private final CinemaCatalogService cinemaCatalogService;

    public CinemaTools(CinemaCatalogService cinemaCatalogService) {
        this.cinemaCatalogService = cinemaCatalogService;
    }

    /**
     * Herramienta MCP que devuelve todas las películas en cartelera con título, género, duración y clasificación.
     *
     * @return Lista de resúmenes de películas.
     */
    @McpTool(name = "getMovies", description = "Devuelve todas las películas en cartelera con título, género, duración y clasificación")
    public List<MovieSummaryDto> getMovies() {
        return cinemaCatalogService.getCatalog().stream()
                .map(movie -> new MovieSummaryDto(
                        movie.title(),
                        movie.genre(),
                        movie.duration(),
                        movie.rating(),
                        movie.audience()
                ))
                .toList();
    }

    /**
     * Herramienta MCP que busca los horarios de una película por su título (búsqueda insensible a mayúsculas y minúsculas).
     *
     * @param title Nombre o título de la película.
     * @return Objeto MovieScheduleDto con la película, sus horarios o un mensaje si no existe.
     */
    @McpTool(name = "getMovieSchedule", description = "Busca los horarios disponibles de una película por su título (insensible a case)")
    public MovieScheduleDto getMovieSchedule(
            @McpToolParam(description = "Nombre o título de la película", required = true) String title
    ) {
        if (title == null || title.isBlank()) {
            return MovieScheduleDto.notFound("Sin título especificado");
        }

        List<Movie> matches = cinemaCatalogService.findByTitle(title);
        if (matches.isEmpty()) {
            return MovieScheduleDto.notFound(title);
        }

        Movie movie = matches.get(0);
        return MovieScheduleDto.found(movie.title(), movie.schedules());
    }

    /**
     * Herramienta MCP que busca la clasificación y público recomendado de una película por su título (insensible a case).
     *
     * @param title Nombre o título de la película.
     * @return Objeto MovieAudienceDto con la clasificación y público recomendado o un mensaje si no existe.
     */
    @McpTool(name = "getMovieAudience", description = "Obtiene la clasificación y el público recomendado de una película por su título (insensible a case)")
    public MovieAudienceDto getMovieAudience(
            @McpToolParam(description = "Nombre o título de la película", required = true) String title
    ) {
        if (title == null || title.isBlank()) {
            return MovieAudienceDto.notFound("Sin título especificado");
        }

        List<Movie> matches = cinemaCatalogService.findByTitle(title);
        if (matches.isEmpty()) {
            return MovieAudienceDto.notFound(title);
        }

        Movie movie = matches.get(0);
        return MovieAudienceDto.found(movie.title(), movie.rating(), movie.audience());
    }
}
