package com.api.doarmais.dtos.request;

import com.api.doarmais.models.tabelas.AnuncioModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PropostaRequestDto {

    @NotNull(message = "Data da proposta não pode ser nula")
    private LocalDateTime dataAgendada;

    @NotNull(message = "Anuncio não pode ser nulo")
    private Integer idAnuncio;

    private List<@Valid ItemPropostaRequestDto> itemPropostaRequestDtoList;

}
