package com.andres.course.agy.springboot.cinemamcpserver.app.repositories;

import com.andres.course.agy.springboot.cinemamcpserver.app.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio de catálogo de cine en memoria.
 */
@Repository
public class CinemaCatalogRepositoryImpl implements CinemaCatalogRepository {

    private static final List<Movie> CATALOG = List.of(
            new Movie(
                    "Moana 2",
                    "Animación / Aventura",
                    "100 min",
                    "4.8/5",
                    "TE - Todo Espectador",
                    List.of("14:00", "16:30", "19:00")
            ),
            new Movie(
                    "Gladiador II",
                    "Acción / Drama Histórico",
                    "148 min",
                    "4.6/5",
                    "+14 - Mayores de 14 años",
                    List.of("15:00", "18:15", "21:30")
            ),
            new Movie(
                    "Wicked",
                    "Musical / Fantasía",
                    "160 min",
                    "4.7/5",
                    "TE - Todo Espectador",
                    List.of("13:30", "17:00", "20:30")
            ),
            new Movie(
                    "Evil Dead Rises",
                    "Terror / Gore",
                    "96 min",
                    "4.4/5",
                    "+18 - Adultos",
                    List.of("19:30", "22:00", "00:15")
            ),
            new Movie(
                    "Minions: Nace un Villano",
                    "Animación / Comedia",
                    "87 min",
                    "4.5/5",
                    "TE - Todo Espectador",
                    List.of("12:00", "14:15", "16:45", "18:45")
            ),
            new Movie(
                    "Toy Story 5",
                    "Animación / Familia",
                    "105 min",
                    "4.9/5",
                    "TE - Todo Espectador",
                    List.of("13:00", "15:30", "18:00", "20:15")
            ),
            new Movie(
                    "El Día de la Revelación",
                    "Suspenso / Ciencia Ficción",
                    "115 min",
                    "4.2/5",
                    "+14 - Mayores de 14 años",
                    List.of("17:00", "19:45", "22:15")
            ),
            new Movie(
                    "Scary Movie: El Regreso",
                    "Comedia / Parodia",
                    "92 min",
                    "4.1/5",
                    "+16 - Mayores de 16 años",
                    List.of("18:30", "21:00", "23:15")
            ),
            new Movie(
                    "Backrooms: La Dimensión Oscura",
                    "Terror Psicológico",
                    "108 min",
                    "4.3/5",
                    "+16 - Mayores de 16 años",
                    List.of("16:00", "18:45", "21:15", "23:30")
            ),
            new Movie(
                    "Obsession",
                    "Thriller / Drama",
                    "112 min",
                    "4.5/5",
                    "+18 - Adultos",
                    List.of("19:15", "21:45")
            )
    );

    @Override
    public List<Movie> getCatalog() {
        return CATALOG;
    }

    @Override
    public List<Movie> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            return CATALOG;
        }
        return CATALOG.stream()
                .filter(m -> m.title().toLowerCase().contains(title.toLowerCase().trim()))
                .toList();
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            return CATALOG;
        }
        return CATALOG.stream()
                .filter(m -> m.genre().toLowerCase().contains(genre.toLowerCase().trim()))
                .toList();
    }

    @Override
    public Optional<Movie> findExactByTitle(String title) {
        if (title == null) {
            return Optional.empty();
        }
        return CATALOG.stream()
                .filter(m -> m.title().equalsIgnoreCase(title.trim()))
                .findFirst();
    }
}
