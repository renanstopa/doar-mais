package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_gerenciar_denuncia")
public class BuscaGerenciarDenunciaViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_tipo_denuncia")
  private Integer idTipoDenuncia;

  @Column(name = "id_situacao")
  private Integer idSituacao;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "data_cricao")
  private String dataCriacao;
}
