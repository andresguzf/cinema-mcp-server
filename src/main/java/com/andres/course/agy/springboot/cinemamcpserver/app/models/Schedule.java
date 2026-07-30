package com.andres.course.agy.springboot.cinemamcpserver.app.models;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad JPA que representa un horario de función disponible para una película.
 */
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String showtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Schedule() {
    }

    public Schedule(String showtime) {
        this.showtime = showtime;
    }

    public Schedule(String showtime, Movie movie) {
        this.showtime = showtime;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) || Objects.equals(showtime, schedule.showtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showtime);
    }
}
