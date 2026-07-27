package com.andres.course.agy.springboot.cinemamcpserver.app.dto;

import java.util.List;

/**
 * DTO que representa la respuesta de horarios para una película.
 *
 * @param title     Título de la película encontrada o solicitada.
 * @param schedules Lista de horarios disponibles.
 * @param message   Mensaje descriptivo del resultado de la búsqueda.
 */
public record MovieScheduleDto(
        String title,
        List<String> schedules,
        String message
) {
    public static MovieScheduleDto found(String title, List<String> schedules) {
        return new MovieScheduleDto(title, schedules, "Horarios encontrados para la película '" + title + "'");
    }

    public static MovieScheduleDto notFound(String requestedTitle) {
        return new MovieScheduleDto(requestedTitle, List.of(), "No se encontró ninguna película en cartelera con el nombre '" + requestedTitle + "'");
    }
}
