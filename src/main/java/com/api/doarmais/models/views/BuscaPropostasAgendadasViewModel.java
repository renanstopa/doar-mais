package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_busca_propostas_agendadas")
public class BuscaPropostasAgendadasViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Column(name = "id_tipo_usuario")
  private Integer idTipoUsuario;

  @Column(name = "id_anuncio")
  private Integer idAnuncio;

  @Column(name = "id_tipo_anuncio")
  private Integer idTipoAnuncio;

  @Column(name = "nome_proposta")
  private String nomeProposta;

  @Column(name = "telefone_proposta")
  private String telefoneProposta;

  @Column(name = "nome_anuncio")
  private String nomeAnuncio;

  @Column(name = "telefone_anuncio")
  private String telefoneAnuncio;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "data_agendada")
  private String dataAgendada;

  @Column(name = "data_filtro")
  private LocalDateTime dataFiltro;
}
