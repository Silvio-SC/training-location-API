package projeto.training_location.service;

import java.util.List;
import java.util.UUID;

import projeto.training_location.model.TrainingLocation;

public interface TrainingLocationService {
    
    List<TrainingLocation> findAll();
    
    TrainingLocation findById(UUID id) ;

    TrainingLocation create(TrainingLocation trainingLocationToCreate);

    TrainingLocation update(UUID id, TrainingLocation trainingLocationToUpdate);

    void delete(UUID id);
}