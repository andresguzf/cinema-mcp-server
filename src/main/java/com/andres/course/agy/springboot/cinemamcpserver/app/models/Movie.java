package com.andres.course.agy.springboot.cinemamcpserver.app.models;

import java.util.List;

/**
 * Modelo inmutable que representa una película en cartelera.
 *
 * @param title      Título de la película.
 * @param genre      Género cinematográfico.
 * @param duration   Duración estimada (ej. "105 min").
 * @param rating     Calificación de la crítica o usuarios (ej. "4.8/5").
 * @param audience   Público recomendado / Clasificación por edad (ej. "TE", "+14", "+18").
 * @param schedules  Lista de horarios disponibles para las funciones.
 */
public record Movie(
        String title,
        String genre,
        String duration,
        String rating,
        String audience,
        List<String> schedules
) {
    public Movie {
        if (schedules != null) {
            schedules = List.copyOf(schedules);
        } else {
            schedules = List.of();
        }
    }
}
