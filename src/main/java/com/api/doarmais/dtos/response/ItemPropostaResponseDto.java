package com.api.doarmais.dtos.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPropostaResponseDto {

    private Integer idItem;
    private Integer quantidade;

}
