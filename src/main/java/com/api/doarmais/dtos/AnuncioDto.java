package com.api.doarmais.dtos;

import com.api.doarmais.models.TipoAnuncioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnuncioDto {
    @NotNull(message = "Tipo Anúncio não pode ser nulo!")
    private TipoAnuncioModel tipoAnuncioModel;

    @NotBlank(message = "Descrição do Anúncio deve ser preenchida!")
    @NotNull(message = "Descrição do Anúncio não pode ser nulo!")
    @Size(max = 400, message = "Descrição do Anúncio deve conter no máximo 400 caracteres!")
    private String descAnuncio;
}
