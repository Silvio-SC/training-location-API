package projeto.training_location.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import projeto.training_location.model.User;
import projeto.training_location.repository.UserRepository;
import projeto.training_location.service.UserService;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        Optional<User> foundedUser = userRepository.findById(id);

        if (!foundedUser.isPresent()) {
            throw new ObjectNotFoundException("User not found", foundedUser);
        }
        return foundedUser.get();
    }

    @Override
    public User create(User userToCreate) {
        User createdUser = userRepository.save(userToCreate);
        return createdUser;
    }

    @Override
    public User update(UUID id, User userToUpdate) {
        findById(id);
        userToUpdate.setId(id);
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    @Override
    public void delete(UUID id) {
        var foundedUser = this.findById(id);
        userRepository.delete(foundedUser);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
