package com.api.doarmais.dtos.response;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemPropostaResponseDto {

    private Integer id;
    private String nome;
    private CategoriaItemModel categoriaItemModel;
    private Integer quantidade;
    private String descricao;

}
