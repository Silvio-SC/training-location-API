package projeto.training_location.controller;

import java.net.URI;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projeto.training_location.controller.dto.AuthenticationDTO;
import projeto.training_location.controller.dto.LoginResponseDTO;
import projeto.training_location.controller.dto.RegisterDTO;
import projeto.training_location.model.User;
import projeto.training_location.model.UserRole;
import projeto.training_location.repository.UserRepository;
import projeto.training_location.security.TokenService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;
   


    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO userToCreate) {
        if (this.userRepository.findByEmail(userToCreate.email()) != null) {
            return ResponseEntity.badRequest().build();
        } 

        String encryptedPassword = new BCryptPasswordEncoder().encode(userToCreate.password());

        User user = new User(userToCreate.name(), userToCreate.email(), encryptedPassword, UserRole.USER);

        this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();

        return ResponseEntity.created(location).body(user); 
    }

    @PostMapping("/register/admin")
    @Transactional
    public ResponseEntity<User> registerAdmin(@RequestBody @Valid RegisterDTO userToCreate) {
        if (this.userRepository.findByEmail(userToCreate.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userToCreate.password());

        User user = new User(userToCreate.name(), userToCreate.email(), encryptedPassword, UserRole.ADMIN);

        this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(user.getId())
            .toUri();

        return ResponseEntity.created(location).body(user); 
    }
}
