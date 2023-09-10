package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "punicao")
public class PunicaoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Column(name = "id_situacao")
  private Integer idSituacao;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_agendada")
  private LocalDateTime dataAgendada;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_cancelamento")
  private LocalDateTime dataCancelamento;

  @Column(name = "motivo")
  private String motivo;
}
