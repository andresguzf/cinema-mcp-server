package com.andres.course.agy.springboot.cinemamcpserver.app.config;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Audience;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Genre;
import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.AudienceRepository;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.GenreRepository;
import com.andres.course.agy.springboot.cinemamcpserver.app.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Componente CommandLineRunner que puebla la base de datos PostgreSQL con el catálogo inicial de películas
 * normalizado (Películas, Géneros, Audiencias, Horarios) si la tabla se encuentra vacía.
 */
@Component
public class CinemaDataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final AudienceRepository audienceRepository;

    public CinemaDataSeeder(
            MovieRepository movieRepository,
            GenreRepository genreRepository,
            AudienceRepository audienceRepository
    ) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.audienceRepository = audienceRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (movieRepository.count() > 0) {
            System.out.println(">>> Base de datos PostgreSQL ya contiene datos de películas. Se omite el seeding.");
            return;
        }

        System.out.println(">>> Migrando catálogo inicial a PostgreSQL...");

        Map<String, Genre> genreCache = new HashMap<>();
        Map<String, Audience> audienceCache = new HashMap<>();

        createMovie("Moana 2", "100 min", "4.8/5", "TE - Todo Espectador",
                List.of("Animación", "Aventura"),
                List.of("14:00", "16:30", "19:00"),
                genreCache, audienceCache);

        createMovie("Gladiador II", "148 min", "4.6/5", "+14 - Mayores de 14 años",
                List.of("Acción", "Drama Histórico"),
                List.of("15:00", "18:15", "21:30"),
                genreCache, audienceCache);

        createMovie("Wicked", "160 min", "4.7/5", "TE - Todo Espectador",
                List.of("Musical", "Fantasía"),
                List.of("13:30", "17:00", "20:30"),
                genreCache, audienceCache);

        createMovie("Evil Dead Rises", "96 min", "4.4/5", "+18 - Adultos",
                List.of("Terror", "Gore"),
                List.of("19:30", "22:00", "00:15"),
                genreCache, audienceCache);

        createMovie("Minions: Nace un Villano", "87 min", "4.5/5", "TE - Todo Espectador",
                List.of("Animación", "Comedia"),
                List.of("12:00", "14:15", "16:45", "18:45"),
                genreCache, audienceCache);

        createMovie("Toy Story 5", "105 min", "4.9/5", "TE - Todo Espectador",
                List.of("Animación", "Familia"),
                List.of("13:00", "15:30", "18:00", "20:15"),
                genreCache, audienceCache);

        createMovie("El Día de la Revelación", "115 min", "4.2/5", "+14 - Mayores de 14 años",
                List.of("Suspenso", "Ciencia Ficción"),
                List.of("17:00", "19:45", "22:15"),
                genreCache, audienceCache);

        createMovie("Scary Movie: El Regreso", "92 min", "4.1/5", "+16 - Mayores de 16 años",
                List.of("Comedia", "Parodia"),
                List.of("18:30", "21:00", "23:15"),
                genreCache, audienceCache);

        createMovie("Backrooms: La Dimensión Oscura", "108 min", "4.3/5", "+16 - Mayores de 16 años",
                List.of("Terror Psicológico"),
                List.of("16:00", "18:45", "21:15", "23:30"),
                genreCache, audienceCache);

        createMovie("Obsession", "112 min", "4.5/5", "+18 - Adultos",
                List.of("Thriller", "Drama"),
                List.of("19:15", "21:45"),
                genreCache, audienceCache);

        System.out.println(">>> Migración de catálogo de cine a PostgreSQL completada con éxito. Se insertaron 10 películas.");
    }

    private void createMovie(
            String title,
            String duration,
            String rating,
            String audienceName,
            List<String> genreNames,
            List<String> schedules,
            Map<String, Genre> genreCache,
            Map<String, Audience> audienceCache
    ) {
        Audience audience = audienceCache.computeIfAbsent(audienceName, name ->
                audienceRepository.findByName(name).orElseGet(() -> audienceRepository.save(new Audience(name)))
        );

        Set<Genre> genres = new LinkedHashSet<>();
        for (String gName : genreNames) {
            Genre genre = genreCache.computeIfAbsent(gName, name ->
                    genreRepository.findByName(name).orElseGet(() -> genreRepository.save(new Genre(name)))
            );
            genres.add(genre);
        }

        Movie movie = new Movie(title, duration, rating, audience, genres, schedules);
        movieRepository.save(movie);
    }
}
