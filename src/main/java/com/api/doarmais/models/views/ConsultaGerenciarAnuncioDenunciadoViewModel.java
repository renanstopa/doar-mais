package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_consulta_gerenciar_usuario_denunciado")
public class ConsultaGerenciarAnuncioDenunciadoViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_tipo_denuncia")
  private Integer idtipoDenuncia;

  @Column(name = "id_anuncio")
  private Integer idAnuncio;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descricao_denuncia")
  private String descricaoDenuncia;

  @Column(name = "data_crica")
  private String dataCriacao;

}
