package com.api.doarmais.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CriarUsuarioDto {

    @NotBlank(message = "Nome do Usuário deve ser preenchido!")
    @NotNull(message = "Nome do Usuário não pode ser nulo!")
    @Size(max = 200, message = "Nome do Usuário deve conter no máximo 200 caracteres!")
    private String txUsuario;

    @NotBlank(message = "Telefone deve ser preenchido!")
    @NotNull(message = "Telefone não pode ser nulo!")
    @Size(min = 11, max = 11, message = "Telefone deve conter exatamente 11 números!")
    private String txTelefone;

    @Size(max = 14, message = "Documento deve conter no máximo 14 números!")
    private String txDocumento;

    @NotBlank(message = "Email deve ser preenchido!")
    @NotNull(message = "Email não pode ser nulo!")
    @Email(message = "Email inválido!")
    @Size(max = 200, message = "Email deve ter no máximo 200 caracteres!")
    private String txEmail;

    @NotBlank(message = "Senha deve ser preenchido!")
    @NotNull(message = "Senha não pode ser nulo!")
    @Size(max = 20, message = "Senha deve ter no máximo 20 caracteres!")
    private String txSenha;

    @NotBlank(message = "CEP deve ser preenchido!")
    @NotNull(message = "CEP não pode ser nulo!")
    @Size(max = 8, message = "CEP deve ter no máximo 8 caracteres!")
    private String txCep;

    @NotNull(message = "Número não pode ser nulo!")
    private Integer cdNumero;

    @NotBlank(message = "Complemente deve ser preenchido!")
    @NotNull(message = "Complemento não pode ser nulo!")
    @Size(max = 100, message = "Compelemento deve conter no máximo 100 caracteres!")
    private String txComplemento;

}
