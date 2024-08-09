package projeto.training_location.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projeto.training_location.service.exception.NotFoundException;
import projeto.training_location.model.TrainingLocation;
import projeto.training_location.repository.TrainingLocationRepository;
import projeto.training_location.service.TrainingLocationService;


@Service
public class TrainingLocationServiceImp implements TrainingLocationService {

    @Autowired
    TrainingLocationRepository trainingLocationRepository;

    public TrainingLocationServiceImp(TrainingLocationRepository trainingLocationRepository) {
        this.trainingLocationRepository = trainingLocationRepository;
    }

    @Override
    public Page<TrainingLocation> findAll(Pageable pageable) {
        return trainingLocationRepository.findAll(pageable);
    }

    @Override
    public TrainingLocation findById(UUID id) {

        Optional<TrainingLocation> foundedTrainingLocation = trainingLocationRepository.findById(id);

        if (!foundedTrainingLocation.isPresent()) {
            throw new NotFoundException("Training location not found");
        }

        return foundedTrainingLocation.get();
    }

    @Override
    public TrainingLocation create(TrainingLocation tlToCreate) {
        TrainingLocation createdTL = trainingLocationRepository.save(tlToCreate);
        return createdTL;
    }

    @Override
    public TrainingLocation update(UUID id, TrainingLocation trainingLocationToUpdate) {
            this.findById(id);

            var tl = trainingLocationRepository.save(trainingLocationToUpdate);
            return tl;
    }

    @Override
    public void delete(UUID id) {
        trainingLocationRepository.deleteById(id);
    }
    
}
