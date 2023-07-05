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
@Table(name = "item_anuncio_proposta")
public class ItemAnuncioPropostaModel {

  @Id
  @ManyToOne
  @JoinColumn(name = "id_proposta")
  private PropostaModel propostaModel;

  @Id
  @ManyToOne
  @JoinColumn(name = "id_item")
  private ItemAnuncioModel itemAnuncioModel;

  @Column(name = "quantidade_solicitada")
  private Integer quantidadeSolicitada;
}
