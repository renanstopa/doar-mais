package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_anuncio")
public class ItemAnuncioModel {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_anuncio")
  private AnuncioModel anuncioModel;

  @ManyToOne
  @JoinColumn(name = "id_categoria_item")
  private CategoriaItemModel categoriaItemModel;

  @Column(name = "quantidade")
  private Integer quantidade;

  @Column(name = "descricao")
  private String descricao;
}
