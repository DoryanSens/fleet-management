package fr.extia.mentoring.fleetmanagement.services;

import fr.extia.mentoring.fleetmanagement.entities.Driver;
import fr.extia.mentoring.fleetmanagement.errors.NotFoundException;
import fr.extia.mentoring.fleetmanagement.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "No Driver found for ID: %S".formatted(id))
               );
    }

    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver update(Driver paramDriver) {
        if(null == paramDriver || null == paramDriver.getId()){
            throw new IllegalArgumentException("No ID provided to update paramDriver: %s".formatted(paramDriver));
        }
        Driver existingDriver = driverRepository.findById(paramDriver.getId())
                .orElseThrow(() -> new NotFoundException(
                        "No Driver found for ID: %S".formatted(paramDriver.getId())
                )
        );

        if(paramDriver.getName() != null){
            existingDriver.setName(paramDriver.getName());
        }

        return driverRepository.save(existingDriver);
    }
}
