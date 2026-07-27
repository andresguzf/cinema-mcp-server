package com.andres.course.agy.springboot.cinemamcpserver.app.tools;

import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieSummaryDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.CinemaCatalogRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Herramientas MCP para consultar información del cine.
 */
@Component
public class CinemaTools {

    private final CinemaCatalogRepository cinemaCatalogRepository;

    public CinemaTools(CinemaCatalogRepository cinemaCatalogRepository) {
        this.cinemaCatalogRepository = cinemaCatalogRepository;
    }

    @McpTool(name = "getMovies", description = "Devuelve todas las películas en cartelera con título, género, duración y clasificación")
    public List<MovieSummaryDto> getMovies() {
        return cinemaCatalogRepository.getCatalog().stream()
                .map(movie -> new MovieSummaryDto(
                        movie.title(),
                        movie.genre(),
                        movie.duration(),
                        movie.rating(),
                        movie.audience()
                ))
                .toList();
    }
}
