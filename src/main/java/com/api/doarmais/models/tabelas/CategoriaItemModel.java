package com.api.doarmais.models.tabelas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoria_item")
public class CategoriaItemModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  private String descricao;
}
