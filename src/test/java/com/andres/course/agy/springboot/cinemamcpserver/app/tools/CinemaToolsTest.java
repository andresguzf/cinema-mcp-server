package com.andres.course.agy.springboot.cinemamcpserver.app.tools;

import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieAudienceDto;
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
        MovieScheduleDto result = cinemaTools.getMovieSchedule("moana");
        assertNotNull(result);
        assertEquals("Moana 2", result.title());
        assertFalse(result.schedules().isEmpty());
        assertTrue(result.message().contains("Horarios encontrados"));
    }

    @Test
    void testGetMovieScheduleNotFound() {
        MovieScheduleDto result = cinemaTools.getMovieSchedule("Matrix Reloaded");
        assertNotNull(result);
        assertTrue(result.schedules().isEmpty());
        assertTrue(result.message().contains("No se encontró ninguna película"));
    }

    @Test
    void testGetMovieAudienceCaseInsensitiveFound() {
        // Probamos con "evil dead"
        MovieAudienceDto result = cinemaTools.getMovieAudience("evil dead");
        assertNotNull(result);
        assertEquals("Evil Dead Rises", result.title());
        assertNotNull(result.rating());
        assertEquals("+18 - Adultos", result.audience());
        assertTrue(result.message().contains("Clasificación y público recomendado encontrados"));
    }

    @Test
    void testGetMovieAudienceNotFound() {
        MovieAudienceDto result = cinemaTools.getMovieAudience("Inception");
        assertNotNull(result);
        assertNull(result.audience());
        assertTrue(result.message().contains("No se encontró ninguna película"));
    }
}
