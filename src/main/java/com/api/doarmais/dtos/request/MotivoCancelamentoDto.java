package com.api.doarmais.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MotivoCancelamentoDto {

    @NotNull(message = "Motivo não pode ser nulo!")
    @NotBlank(message = "Motivo deve ser preenchido!")
    private String motivo;
}
