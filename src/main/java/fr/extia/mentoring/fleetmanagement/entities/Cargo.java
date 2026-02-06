package fr.extia.mentoring.fleetmanagement.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    public String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ADR.class, fetch = FetchType.EAGER)
    @CollectionTable(name="cargo_dangers", joinColumns = @JoinColumn(name="cargo_id"))
    public Set<ADR> dangers;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = TrailerType.class, fetch = FetchType.EAGER)
    @CollectionTable(name="cargo_compatibleTrailers", joinColumns = @JoinColumn(name="cargo_id"))
    public Set<TrailerType> compatibleTrailers;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoadLevel weight;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ADR> getDangers() {
        return dangers;
    }

    public void setDangers(Set<ADR> dangers) {
        this.dangers = dangers;
    }

    public Set<TrailerType> getCompatibleTrailers() {
        return compatibleTrailers;
    }

    public void setCompatibleTrailers(Set<TrailerType> compatibleTrailers) {
        this.compatibleTrailers = compatibleTrailers;
    }

    public LoadLevel getWeight() {
        return weight;
    }

    public void setWeight(LoadLevel weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dangers=" + dangers +
                ", compatibleTrailers=" + compatibleTrailers +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(id, cargo.id) && Objects.equals(description, cargo.description) && Objects.equals(dangers, cargo.dangers) && Objects.equals(compatibleTrailers, cargo.compatibleTrailers) && weight == cargo.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, dangers, compatibleTrailers, weight);
    }
}
