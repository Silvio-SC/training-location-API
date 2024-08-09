package projeto.training_location.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO (
    @NotBlank
    String name, 
    
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    String password
    ) {

}
