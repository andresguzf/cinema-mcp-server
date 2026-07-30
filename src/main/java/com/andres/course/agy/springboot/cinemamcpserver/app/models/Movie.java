package com.andres.course.agy.springboot.cinemamcpserver.app.models;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Entidad JPA que representa una película en cartelera.
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String rating;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "audience_id")
    private Audience audience;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Schedule> schedules = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, String duration, String rating, Audience audience, Set<Genre> genres, List<String> scheduleTimes) {
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.audience = audience;
        if (genres != null) {
            this.genres.addAll(genres);
        }
        if (scheduleTimes != null) {
            for (String time : scheduleTimes) {
                addSchedule(new Schedule(time));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Audience getAudienceEntity() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<Schedule> getScheduleEntities() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setMovie(this);
    }

    // --- Record Compatibility Methods for CinemaTools & DTOs ---

    public String title() {
        return title;
    }

    public String genre() {
        if (genres == null || genres.isEmpty()) {
            return "";
        }
        return genres.stream().map(Genre::getName).collect(Collectors.joining(" / "));
    }

    public String duration() {
        return duration;
    }

    public String rating() {
        return rating;
    }

    public String audience() {
        return audience != null ? audience.getName() : "";
    }

    public List<String> schedules() {
        if (schedules == null) {
            return List.of();
        }
        return schedules.stream().map(Schedule::getShowtime).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) || Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
