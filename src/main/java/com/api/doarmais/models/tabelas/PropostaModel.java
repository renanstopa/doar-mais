package com.api.doarmais.models.tabelas;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "proposta")
public class PropostaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private UsuarioModel usuarioModel;

  @ManyToOne
  @JoinColumn(name = "id_anuncio")
  private AnuncioModel anuncioModel;

  @ManyToOne
  @JoinColumn(name = "id_situacao")
  private SituacaoModel situacaoModel;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_agendada")
  private LocalDateTime dataAgendada;
}
