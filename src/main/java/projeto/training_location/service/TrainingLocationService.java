package projeto.training_location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import projeto.training_location.model.TrainingLocation;

public interface TrainingLocationService {
    
    Page<TrainingLocation> findAll(Pageable pageable);
    
    TrainingLocation findById(UUID id);

    TrainingLocation create(TrainingLocation trainingLocationToCreate);

    TrainingLocation update(UUID id, TrainingLocation trainingLocationToUpdate);

    void delete(UUID id);
}