package com.api.doarmais.dtos.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PropostaResponseDto {

  private Integer id;
  private LocalDateTime dataAgendada;
  List<ItemPropostaResponseDto> itemList;
}
