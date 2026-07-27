package com.andres.course.agy.springboot.cinemamcpserver.app.tools;

import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieScheduleDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieSummaryDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.CinemaCatalogRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CinemaToolsTest {

    private CinemaTools cinemaTools;

    @BeforeEach
    void setUp() {
        CinemaCatalogRepositoryImpl repository = new CinemaCatalogRepositoryImpl();
        cinemaTools = new CinemaTools(repository);
    }

    @Test
    void testGetMoviesReturnsAllCartelera() {
        List<MovieSummaryDto> movies = cinemaTools.getMovies();
        assertNotNull(movies);
        assertEquals(10, movies.size());
    }

    @Test
    void testGetMovieScheduleCaseInsensitiveFound() {
        // Búsqueda en minúsculas para "moana 2"
        MovieScheduleDto result = cinemaTools.getMovieSchedule("moana");
        assertNotNull(result);
        assertEquals("Moana 2", result.title());
        assertFalse(result.schedules().isEmpty());
        assertTrue(result.message().contains("Horarios encontrados"));
    }

    @Test
    void testGetMovieScheduleNotFound() {
        // Búsqueda de una película inexistente
        MovieScheduleDto result = cinemaTools.getMovieSchedule("Matrix Reloaded");
        assertNotNull(result);
        assertTrue(result.schedules().isEmpty());
        assertTrue(result.message().contains("No se encontró ninguna película"));
    }
}
