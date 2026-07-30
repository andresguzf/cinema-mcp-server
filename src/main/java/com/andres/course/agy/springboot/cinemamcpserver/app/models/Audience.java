package com.andres.course.agy.springboot.cinemamcpserver.app.models;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad JPA que representa la clasificación del público / edad recomendada.
 */
@Entity
@Table(name = "audiences")
public class Audience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Audience() {
    }

    public Audience(String name) {
        this.name = name;
    }

    public Audience(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audience audience = (Audience) o;
        return Objects.equals(name, audience.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
