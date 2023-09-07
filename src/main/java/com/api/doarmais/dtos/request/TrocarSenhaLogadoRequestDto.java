package com.api.doarmais.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrocarSenhaLogadoRequestDto {

  @NotNull(message = "Senha atual não pode ser nula")
  @NotBlank(message = "Senha atual deve ser preenchida")
  @Size(max = 20, message = "Senha atual deve ter no máximo 20 caracteres")
  private String senhaAtual;

  @NotNull(message = "Nova senha não pode ser nula")
  @NotBlank(message = "Nova senha deve ser preenchida")
  @Size(max = 20, message = "Nova senha deve ter no máximo 20 caracteres")
  private String novaSenha;

  @NotNull(message = "Confirmação de senha não pode ser nula")
  @NotBlank(message = "Confirmação de senha deve ser preenchida")
  @Size(max = 20, message = "Confirmação de senha deve ter no máximo 20 caracteres")
  private String confirmaSenha;
}
