package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_consulta_gerenciar_usuario_denunciado")
public class ConsultaGerenciarUsuarioDenunciadoViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_tipo_denuncia")
  private Integer idTipoDenuncia;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "usuario_denunciado")
  private String usuarioDenunciado;

  @Column(name = "descricao_denuncia")
  private String descricaoDenuncia;

  @Column(name = "data_criacao")
  private String dataCriacao;
}
