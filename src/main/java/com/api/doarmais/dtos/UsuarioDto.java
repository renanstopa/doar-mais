package com.api.doarmais.dtos;

import com.api.doarmais.models.TipoUsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDto {

    @NotNull(message = "Usuário não pode ser nulo!")
    private TipoUsuarioModel tipoUsuarioModel;

    @NotBlank(message = "Nome do Usuário deve ser preenchido!")
    @NotNull(message = "Nome do Usuário não pode ser nulo!")
    @Size(max = 200, message = "Nome do Usuário deve conter no máximo 200 caracteres!")
    private String txUsuario;

    @NotBlank(message = "Telefone deve ser preenchido!")
    @NotNull(message = "Telefone não pode ser nulo!")
    @Size(min = 11, max = 11, message = "Telefone deve conter exatamente 11 números!")
    private String txTelefone;

    @Size(min = 14, max = 14, message = "Documento deve conter exatamente 14 números!")
    private String txDocumento;

    private String txSenha;
    private String txEmail;

}
