package fr.extia.mentoring.fleetmanagement.controllers;

import fr.extia.mentoring.fleetmanagement.entities.LoadLevel;
import fr.extia.mentoring.fleetmanagement.entities.Tractor;
import fr.extia.mentoring.fleetmanagement.entities.Trailer;
import fr.extia.mentoring.fleetmanagement.entities.TrailerType;
import fr.extia.mentoring.fleetmanagement.services.TrailerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trailers")
public class TrailerController {
    private final TrailerService trailerService;

    public TrailerController(TrailerService trailerService) {
        this.trailerService = trailerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trailer create(@RequestBody Trailer trailer) {
        return trailerService.create(trailer);
    }

    @GetMapping
    public List<Trailer> findAll() {
        return trailerService.findAll();
    }

    @GetMapping("/{id}")
    public  Trailer findById(@PathVariable Long id) {
        return trailerService.findById(id);
    }

    @GetMapping("/type/{type}")
    public List<Trailer> findByType(@PathVariable String type) {
        TrailerType enumTrailerType = TrailerType.valueOf(type);
        return trailerService.findByType(enumTrailerType);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        trailerService.delete(id);
    }

    @PutMapping
    public Trailer updateTrailer(@RequestBody Trailer trailer) {
        return trailerService.update(trailer);
    }
}
