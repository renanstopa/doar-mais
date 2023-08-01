package com.api.doarmais.models.views;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_anuncio")
public class BuscaAnuncioViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_tipo_anuncio")
  private Integer idTipoAnuncio;

  @Column(name = "id_tipo_usuario")
  private Integer idTipoUsuario;

  @Column(name = "nome")
  private String nome;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "data_inicio_disponibilidade")
  private LocalDateTime dataInicioDisponibilidade;

  @Column(name = "data_fim_disponibilidade")
  private LocalDateTime dataFimDisponibilidade;

  @Column(name = "cidade")
  private String cidade;
}
