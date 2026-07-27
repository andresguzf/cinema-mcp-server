package com.andres.course.agy.springboot.cinemamcpserver.app.dto;

/**
 * DTO que representa el resumen de una película en cartelera.
 *
 * @param title     Título de la película.
 * @param genre     Género cinematográfico.
 * @param duration  Duración estimada.
 * @param rating    Calificación de la crítica/usuarios.
 * @param audience  Público recomendado / Clasificación por edad.
 */
public record MovieSummaryDto(
        String title,
        String genre,
        String duration,
        String rating,
        String audience
) {
}
