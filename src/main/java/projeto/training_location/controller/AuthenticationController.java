package projeto.training_location.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import projeto.training_location.model.User;
import projeto.training_location.model.UserRole;
import projeto.training_location.model.DTO.AuthenticationDTO;
import projeto.training_location.model.DTO.LoginResponseDTO;
import projeto.training_location.model.DTO.RegisterDTO;
import projeto.training_location.repository.UserRepository;
import projeto.training_location.security.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {

        var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((User )auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO userToCreate) {
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
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterDTO userToCreate) {
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
