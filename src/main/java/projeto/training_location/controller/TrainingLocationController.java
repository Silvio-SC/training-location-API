package projeto.training_location.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import projeto.training_location.infra.helper.MyBeanUtils;
import projeto.training_location.model.TrainingLocation;
import projeto.training_location.service.TrainingLocationService;

@RestController
@RequestMapping("/training-location")
@SecurityRequirement(name = "bearer-key")
public class TrainingLocationController {
    
    @Autowired
    private TrainingLocationService trainingLocationService;

    @GetMapping()
    public ResponseEntity<Page<TrainingLocation>> findAll(Pageable pageable) {

        var locations = trainingLocationService.findAll(pageable);

        return ResponseEntity.ok(locations);
    }

    // @GetMapping()
    // public ResponseEntity<Page<TrainingLocation>> findAll(
    //    @PageableDefault(size = 2, sort = {"name"})Pageable pageable) {
    //
    //     var locations = trainingLocationService.findAll(pageable);
    //
    //     return ResponseEntity.ok(locations);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingLocation> findById(@PathVariable UUID id) throws NotFoundException {
        var location = trainingLocationService.findById(id);
        return ResponseEntity.ok(location); 
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<TrainingLocation> create(
        @RequestBody TrainingLocation tlToCreate){
            var tl = trainingLocationService.create(tlToCreate);

        return ResponseEntity.ok(tl);
    }

    @PatchMapping("/{id}")
    @Transactional
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
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        trainingLocationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
