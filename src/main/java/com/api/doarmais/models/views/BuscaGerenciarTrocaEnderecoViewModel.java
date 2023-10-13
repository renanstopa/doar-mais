package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_gerenciar_troca_endereco")
public class BuscaGerenciarTrocaEnderecoViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_situacao")
  private Integer idSituacao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cep")
  private String cep;
}
