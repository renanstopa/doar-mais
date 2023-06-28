package com.api.doarmais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDto {

    @NotBlank(message = "CEP deve ser preenchido!")
    @NotNull(message = "CEP não pode ser nulo!")
    @Size(max = 8, message = "CEP deve ter no máximo 8 caracteres!")
    private String txCep;

    @NotBlank(message = "Cidade deve ser preenchido!")
    @NotNull(message = "Cidade não pode ser nulo!")
    @Size(max = 150, message = "Cidade deve ter no máximo 150 caracteres!")
    private String txCidade;

    @NotBlank(message = "UF deve ser preenchido!")
    @NotNull(message = "UF não pode ser nulo!")
    @Size(max = 2, message = "UF deve ter no máximo 2 caracteres!")
    private String txUf;

    @NotBlank(message = "Bairro deve ser preenchido!")
    @NotNull(message = "Bairro não pode ser nulo!")
    @Size(max = 150, message = "Bairro deve ter no máximo 150 caracteres!")
    private String txBairro;

    @NotBlank(message = "Logradouro deve ser preenchido!")
    @NotNull(message = "Logradouro não pode ser nulo!")
    @Size(max = 200, message = "Logradouro deve ter no máximo 200 caracteres!")
    private String txLogradouro;

    @NotNull(message = "Número não pode ser nulo!")
    private Integer cdNumero;

    @Size(max = 100, message = "Compelemento deve conter no máximo 100 caracteres!")
    private String txComplemento;

}
