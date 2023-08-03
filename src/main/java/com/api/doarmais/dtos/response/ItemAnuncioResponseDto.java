package com.api.doarmais.dtos.response;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import lombok.Data;

@Data
public class ItemAnuncioResponseDto {

  private Integer id;
  private String nome;
  private CategoriaItemModel categoriaItemModel;
  private Integer quantidade;
  private String descricao;
}
