package com.api.doarmais.dtos;

import com.api.doarmais.models.AnuncioModel;
import com.api.doarmais.models.CategoriaItemModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemAnuncioDto {

    @NotNull(message = "Anúncio não pode ser nulo!")
    private AnuncioModel anuncioModel;

    @NotNull(message = "Categoria do item não pode ser nula!")
    private CategoriaItemModel categoriaItemModel;

    @NotNull(message = "Quantidade do Item não pode ser nula!")
    private Integer quantidadeItem;

}
