package com.api.doarmais.models.tabelas;

import com.api.doarmais.dtos.request.EditarItemAnuncioRequestDto;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_anuncio")
  private AnuncioModel anuncioModel;

  @ManyToOne
  @JoinColumn(name = "id_categoria_item")
  private CategoriaItemModel categoriaItemModel;

  @Column(name = "nome")
  private String nome;

  @Column(name = "quantidade")
  private Integer quantidade;

  @Column(name = "descricao")
  private String descricao;

    public Boolean verificarTrocaitem(EditarItemAnuncioRequestDto itemDto) {
      if(!this.getNome().equals(itemDto.getNome()))
        return true;

      return !this.getQuantidade().equals(itemDto.getQuantidade());
    }
}
