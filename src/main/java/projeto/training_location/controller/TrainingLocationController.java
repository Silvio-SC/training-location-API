package projeto.training_location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.training_location.helper.MyBeanUtils;
import projeto.training_location.model.TrainingLocation;
import projeto.training_location.service.TrainingLocationService;

@RestController
@RequestMapping("/training-location")
public class TrainingLocationController {
    
    @Autowired
    private TrainingLocationService trainingLocationService;

    @GetMapping()
    public ResponseEntity<List<TrainingLocation>> findAll(){

        var locations = trainingLocationService.findAll();

        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingLocation> findById(@PathVariable UUID id) throws NotFoundException {
        var location = trainingLocationService.findById(id);
        return ResponseEntity.ok(location); 
    }

    @PostMapping()
    public ResponseEntity<TrainingLocation> create(
        @RequestBody TrainingLocation tlToCreate){
            var tl = trainingLocationService.create(tlToCreate);

        return ResponseEntity.ok(tl);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrainingLocation> update(
        @PathVariable UUID id,
        @RequestBody TrainingLocation trainingLocationToUpdate
    ) throws NotFoundException{

        var location = trainingLocationService.findById(id);

        MyBeanUtils.copyNonNullProperties(trainingLocationToUpdate, location); 

        var tl = trainingLocationService.update(id, location);
        return ResponseEntity.ok(tl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        trainingLocationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
