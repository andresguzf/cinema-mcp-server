package com.andres.course.agy.springboot.cinemamcpserver.app.dto;

/**
 * DTO que representa la respuesta con la clasificación y público recomendado de una película.
 *
 * @param title    Título de la película encontrada o solicitada.
 * @param rating   Calificación de la crítica/usuarios.
 * @param audience Público recomendado / Clasificación por edad (ej. "TE - Todo Espectador", "+14", "+18").
 * @param message  Mensaje descriptivo del resultado de la búsqueda.
 */
public record MovieAudienceDto(
        String title,
        String rating,
        String audience,
        String message
) {
    public static MovieAudienceDto found(String title, String rating, String audience) {
        return new MovieAudienceDto(
                title,
                rating,
                audience,
                "Clasificación y público recomendado encontrados para '" + title + "'"
        );
    }

    public static MovieAudienceDto notFound(String requestedTitle) {
        return new MovieAudienceDto(
                requestedTitle,
                null,
                null,
                "No se encontró ninguna película en cartelera con el nombre '" + requestedTitle + "'"
        );
    }
}
