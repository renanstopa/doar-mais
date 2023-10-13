package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_consulta_gerenciar_punicao")
public class ConsultaGerenciarPunicaoViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "data_agendada")
  private String dataAgendada;

  @Column(name = "data_cancelamento")
  private String dataCancelamento;

  @Column(name = "motivo")
  private String motivo;

}
