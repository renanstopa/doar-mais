package com.api.doarmais.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPropostaRequestDto {

  @NotNull(message = "Item não pode ser nulo")
  private Integer idItem;

  @NotNull(message = "Quantidade não pode ser nula")
  private Integer quantidade;
}
