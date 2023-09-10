package com.api.doarmais.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PropostaRequestDto {

  @NotNull(message = "Data da proposta não pode ser nula")
  private LocalDateTime dataAgendada;

  @NotNull(message = "Anuncio não pode ser nulo")
  private Integer idAnuncio;

  private List<@Valid ItemPropostaRequestDto> itemPropostaRequestDtoList;
}
