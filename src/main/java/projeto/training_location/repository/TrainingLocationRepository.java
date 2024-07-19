package projeto.training_location.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.training_location.model.TrainingLocation;

public interface TrainingLocationRepository extends JpaRepository<TrainingLocation, UUID> {
    
}
