package com.api.doarmais.models.views;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_consulta_anuncio")
public class ConsultaAnuncioViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_usuario_criador")
  private Integer idUsuarioCriador;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "data_inicio_disponibilidade")
  private LocalDateTime dataInicioDisponibilidade;

  @Column(name = "data_fim_disponibilidade")
  private LocalDateTime dataFimDisponibilidade;

  @Column(name = "endereco_completo")
  private String enderecoCompleto;

  @Column(name = "nome")
  private String nome;

  @Column(name = "telefone")
  private String telefone;
}
