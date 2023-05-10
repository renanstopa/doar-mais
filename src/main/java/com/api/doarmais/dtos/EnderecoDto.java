package com.api.doarmais.dtos;

import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoDto {

    @NotNull(message = "Usuário não pode ser nulo!")
    private UsuarioModel usuarioModel;

    @NotBlank(message = "Cep deve ser preenchido!")
    @NotNull(message = "Cep não pode ser nulo")
    @Size(min = 9, max = 9, message = "Cep deve conter exatamente 9 caracteres!")
    private String txCep;

    @NotNull(message = "Número não pode ser nulo!")
    private Integer cdNumero;

    @NotBlank(message = "Complemente deve ser preenchido!")
    @NotNull(message = "Complemento não pode ser nulo!")
    @Size(max = 100, message = "Compelemento deve conter no máximo 100 caracteres!")
    private String txComplemento;

}
