package com.api.doarmais.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetRequestDto {

  @NotNull(message = "Email não pode ser nulo")
  @NotBlank(message = "Email deve ser preenchido")
  @Size(message = "Email deve ter no máximo 200 caracteres")
  @Email(message = "Email inválido")
  private String email;
}
