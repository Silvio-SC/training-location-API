package projeto.training_location.controller.dto;

public record LoginResponseDTO(String token) {

    public LoginResponseDTO (String token){
        this.token = token;
    }
}