package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_gerenciar_contas")
public class BuscaGerenciarContasViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_tipo_usuario")
  private Integer idTipoUsuario;

  @Column(name = "id_situacao")
  private Integer idSituacao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "desc_tipo_usuario")
  private String descTipoUsuario;

  @Column(name = "telefone")
  private String telefone;
}
