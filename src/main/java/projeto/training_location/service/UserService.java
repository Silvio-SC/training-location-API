package projeto.training_location.service;

import java.util.List;
import java.util.UUID;

import projeto.training_location.model.User;

public interface UserService {

    List<User> findAll();
    
    User findById(UUID id);

    User findByEmail(String email);

    User create(User userToCreate);

    User update(UUID id, User userToUpdate);

    void delete(UUID id);
}
