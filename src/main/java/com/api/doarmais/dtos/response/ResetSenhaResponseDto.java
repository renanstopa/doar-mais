package com.api.doarmais.dtos.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ResetSenhaResponseDto {

  private String emailUsuario;
  private LocalDateTime dataValidade;
}
