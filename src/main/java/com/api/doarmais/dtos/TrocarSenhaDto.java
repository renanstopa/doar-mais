package com.api.doarmais.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrocarSenhaDto {

    @NotNull(message = "Senha não pode ser nula")
    @NotBlank(message = "Senha deve ser preenchida")
    @Size(max = 20, message = "Senha deve ter no máximo 20 caracteres")
    private String senha;

    @NotNull(message = "Confirmação de senha não pode ser nula")
    @NotBlank(message = "Confirmação de senha deve ser preenchida")
    @Size(max = 20, message = "Confirmação de senha deve ter no máximo 20 caracteres")
    private String confirmaSenha;

}
