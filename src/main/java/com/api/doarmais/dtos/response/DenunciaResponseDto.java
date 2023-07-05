package com.api.doarmais.dtos.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DenunciaResponseDto {

  private String descricao;
  private LocalDateTime dataCriacao;
}
