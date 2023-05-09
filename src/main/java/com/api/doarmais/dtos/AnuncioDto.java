package com.api.doarmais.dtos;

import com.api.doarmais.models.TipoAnuncioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnuncioDto {
    @NotNull
    private TipoAnuncioModel tipoAnuncioModel;

    @NotBlank
    @Size(max = 400)
    private String descAnuncio;
}
