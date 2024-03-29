package com.api.doarmais.dtos.request;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditarItemAnuncioRequestDto {

  @NotNull(message = "Id do item não pode ser nulo")
  private Integer id;

  @NotBlank(message = "Nome do Produto deve ser preenchido!")
  @NotNull(message = "Nome do Produto não pode ser nulo!")
  @Size(max = 60, message = "Nome do Produto deve conter no máximo 60 caracteres!")
  private String nome;

  private CategoriaItemModel categoriaItemModel;

  @NotNull(message = "Quantidade não pode ser nula!")
  @Min(value = 1, message = "Quantidade deve ser no mímino 1")
  private Integer quantidade;

  @NotBlank(message = "Descrição do Produto deve ser preenchida!")
  @NotNull(message = "Descrição do Produto não pode ser nula!")
  @Size(max = 250, message = "Descrição do Produto deve conter no máximo 250 caracteres!")
  private String descricao;
}
