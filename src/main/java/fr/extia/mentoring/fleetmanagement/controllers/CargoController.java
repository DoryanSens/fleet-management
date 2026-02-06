package fr.extia.mentoring.fleetmanagement.controllers;

import fr.extia.mentoring.fleetmanagement.entities.Cargo;
import fr.extia.mentoring.fleetmanagement.services.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargos")
public class CargoController {
    private final CargoService cargoService;
    
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cargo create(@RequestBody Cargo cargo) {
        return cargoService.create(cargo);
    }

    @GetMapping
    public List<Cargo> findAll() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    public Cargo findById(@PathVariable Long id) {
        return cargoService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cargoService.delete(id);
    }

    @PutMapping
    public Cargo update(@RequestBody Cargo cargo) {
        return cargoService.update(cargo);
    }
}
