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
@Table(name = "anuncio")
public class AnuncioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_tipo_anuncio")
  private TipoAnuncioModel tipoAnuncioModel;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "cep")
  private String cep;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "uf")
  private String uf;
}
