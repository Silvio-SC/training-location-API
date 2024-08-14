package projeto.training_location.model.dto;

public record DataAddress(
    String logradouro,
    String bairro,
    String cep,
    String numero,
    String complemento,
    String cidade,
    String uf
) {

}

