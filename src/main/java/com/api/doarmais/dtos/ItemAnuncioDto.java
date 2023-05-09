package com.api.doarmais.dtos;

import com.api.doarmais.models.AnuncioModel;
import com.api.doarmais.models.CategoriaItemModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemAnuncioDto {
    @NotNull
    private AnuncioModel anuncioModel;

    @NotNull
    private CategoriaItemModel categoriaItemModel;

    @NotNull
    private Integer quantidadeItem;
}
