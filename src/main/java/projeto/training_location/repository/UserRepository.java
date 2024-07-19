package projeto.training_location.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.training_location.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
