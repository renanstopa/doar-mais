package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_gerenciar_contas_bloqueadas")
public class BuscaGerenciarContaBloqueadaViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "telefone")
  private String telefone;
}
