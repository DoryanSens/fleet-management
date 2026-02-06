package fr.extia.mentoring.fleetmanagement.repositories;

import fr.extia.mentoring.fleetmanagement.entities.Trailer;
import fr.extia.mentoring.fleetmanagement.entities.TrailerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrailerRepository extends JpaRepository<Trailer, Long> {
    public List<Trailer> findAllByType(TrailerType type);
}
