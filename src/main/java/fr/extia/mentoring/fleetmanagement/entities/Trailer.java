package fr.extia.mentoring.fleetmanagement.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Trailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TrailerType type;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TrailerType getType() {
        return type;
    }

    public void setType(TrailerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Trailer trailer = (Trailer) o;
        return Objects.equals(id, trailer.id) && Objects.equals(name, trailer.name) && type == trailer.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
