package com.andres.course.agy.springboot.cinemamcpserver.app.tools;

import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieAudienceDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieScheduleDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.dto.MovieSummaryDto;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Audience;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Genre;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.CinemaCatalogRepository;
import com.andres.course.agy.springboot.cinemamcpserver.app.services.CinemaCatalogService;
import com.andres.course.agy.springboot.cinemamcpserver.app.services.CinemaCatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CinemaToolsTest {

    private CinemaTools cinemaTools;

    @BeforeEach
    void setUp() {
        CinemaCatalogRepository repository = Mockito.mock(CinemaCatalogRepository.class);

        Movie moana = new Movie(
                "Moana 2",
                "100 min",
                "4.8/5",
                new Audience("TE - Todo Espectador"),
                Set.of(new Genre("Animación"), new Genre("Aventura")),
                List.of("14:00", "16:30", "19:00")
        );

        Movie evilDead = new Movie(
                "Evil Dead Rises",
                "96 min",
                "4.4/5",
                new Audience("+18 - Adultos"),
                Set.of(new Genre("Terror"), new Genre("Gore")),
                List.of("19:30", "22:00", "00:15")
        );

        List<Movie> catalog = List.of(moana, evilDead);

        when(repository.getCatalog()).thenReturn(catalog);
        when(repository.findByTitle("moana")).thenReturn(List.of(moana));
        when(repository.findByTitle("evil dead")).thenReturn(List.of(evilDead));
        when(repository.findByTitle("Matrix Reloaded")).thenReturn(List.of());
        when(repository.findByTitle("Inception")).thenReturn(List.of());

        CinemaCatalogService cinemaCatalogService = new CinemaCatalogServiceImpl(repository);
        cinemaTools = new CinemaTools(cinemaCatalogService);
    }

    @Test
    void testGetMoviesReturnsAllCartelera() {
        List<MovieSummaryDto> movies = cinemaTools.getMovies();
        assertNotNull(movies);
        assertEquals(2, movies.size());
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
