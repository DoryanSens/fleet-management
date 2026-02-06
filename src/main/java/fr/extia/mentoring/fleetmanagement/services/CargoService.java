package fr.extia.mentoring.fleetmanagement.services;

import fr.extia.mentoring.fleetmanagement.entities.Cargo;
import fr.extia.mentoring.fleetmanagement.errors.NotFoundException;
import fr.extia.mentoring.fleetmanagement.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
  private final CargoRepository cargoRepository;

  public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
  }

  public Cargo create(Cargo cargo) {
      return cargoRepository.save(cargo);
  }

  public List<Cargo> findAll() {
      return this.cargoRepository.findAll();
  }

  public Cargo findById(Long id) {
      return cargoRepository.findById(id).orElseThrow(() -> new NotFoundException(
              "No Cargo found for ID: %S".formatted(id))
      );
  }

  public void delete(Long id) {
      this.cargoRepository.deleteById(id);
  }

  public Cargo update(Cargo paramCargo) {
      if(paramCargo == null || paramCargo.getId()==null){
          throw new IllegalArgumentException("No ID provided to update paramCargo: %s".formatted(paramCargo));
      }

      Cargo existingCargo = cargoRepository.findById(paramCargo.getId())
              .orElseThrow(() -> new NotFoundException(
                      "No Cargo found for ID: %S".formatted(paramCargo.getId())
              )
      );

      if(paramCargo.getDescription() != null &&  !paramCargo.getDescription().isBlank()){
          existingCargo.setDescription(paramCargo.getDescription());
      }

      if(paramCargo.getDangers() != null){
          existingCargo.setDangers(paramCargo.getDangers());
      }

      if(paramCargo.getCompatibleTrailers() != null){
          existingCargo.setCompatibleTrailers(paramCargo.getCompatibleTrailers());
      }

      if(paramCargo.getWeight() != null){
          existingCargo.setWeight(paramCargo.getWeight());
      }

      return cargoRepository.save(existingCargo);
  }
}
