package projeto.training_location.service;

import java.util.UUID;

import projeto.training_location.model.User;

public interface UserService {
    
    User findById(UUID id);

    User create(User userToCreate);

    User update(UUID id, User userToUpdate);

    void delete(UUID id);
}
