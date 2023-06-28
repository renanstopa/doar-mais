package com.api.doarmais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AtualizarDadosDto {

    @NotBlank(message = "Nome do Usuário deve ser preenchido!")
    @NotNull(message = "Nome do Usuário não pode ser nulo!")
    @Size(max = 200, message = "Nome do Usuário deve conter no máximo 200 caracteres!")
    private String txUsuario;

    @NotBlank(message = "Telefone deve ser preenchido!")
    @NotNull(message = "Telefone não pode ser nulo!")
    @Size(min = 10, max = 11, message = "Telefone deve conter entre 10 e 11 números!")
    private String txTelefone;

}
