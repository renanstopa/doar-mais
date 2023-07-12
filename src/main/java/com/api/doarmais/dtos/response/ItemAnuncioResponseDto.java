package com.api.doarmais.dtos.response;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemAnuncioResponseDto {

    private String nome;

    private CategoriaItemModel categoriaItemModel;

    private Integer quantidade;

    private String descricao;

}
