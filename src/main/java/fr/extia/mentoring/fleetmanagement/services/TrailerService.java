package fr.extia.mentoring.fleetmanagement.services;

import fr.extia.mentoring.fleetmanagement.entities.Cargo;
import fr.extia.mentoring.fleetmanagement.entities.Driver;
import fr.extia.mentoring.fleetmanagement.entities.Trailer;
import fr.extia.mentoring.fleetmanagement.entities.TrailerType;
import fr.extia.mentoring.fleetmanagement.errors.NotFoundException;
import fr.extia.mentoring.fleetmanagement.repositories.TrailerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailerService {
    private final TrailerRepository trailerRepository;

    public TrailerService(TrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    public Trailer create(Trailer trailer) {
        return trailerRepository.save(trailer);
    }

    public List<Trailer> findAll() {
        return this.trailerRepository.findAll();
    }

    public Trailer findById(Long id) {
        return trailerRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "No Trailer found for ID: %S".formatted(id))
        );
    }

    public List<Trailer> findByType(TrailerType type){
        return trailerRepository.findAllByType(type);
    }

    public void delete(Long Id) {
        trailerRepository.deleteById(Id);
    }

    public  Trailer update(Trailer paramTrailer) {
        {
            if(null == paramTrailer || null == paramTrailer.getId()){
                throw new IllegalArgumentException("No ID provided to update paramTrailer: %s".formatted(paramTrailer));
            }
            Trailer existingTrailer = trailerRepository.findById(paramTrailer.getId())
                    .orElseThrow(() -> new NotFoundException(
                                    "No Trailer found for ID: %S".formatted(paramTrailer.getId())
                            )
                    );

            if(paramTrailer.getName() != null && !paramTrailer.getName().isBlank()){
                existingTrailer.setName(paramTrailer.getName());
            }

            if(paramTrailer.getType() != null){
                existingTrailer.setType(paramTrailer.getType());
            }

            return trailerRepository.save(existingTrailer);
        }
    }

}
