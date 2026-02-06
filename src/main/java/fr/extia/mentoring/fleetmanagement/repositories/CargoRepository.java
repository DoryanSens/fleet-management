package fr.extia.mentoring.fleetmanagement.repositories;

import fr.extia.mentoring.fleetmanagement.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
