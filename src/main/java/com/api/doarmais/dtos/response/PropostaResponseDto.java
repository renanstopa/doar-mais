package com.api.doarmais.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PropostaResponseDto {

    private Integer id;
    private LocalDateTime dataAgendada;
    List<ItemPropostaResponseDto> itemList;

}
