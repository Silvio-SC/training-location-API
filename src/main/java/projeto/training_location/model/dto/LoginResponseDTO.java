package projeto.training_location.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(
    @NotBlank
    String token
    ) {
}