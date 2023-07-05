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
@Table(name = "denuncia")
public class DenunciaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_tipo_denuncia")
  private TipoDenunciaModel tipoDenunciaModel;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private UsuarioModel usuarioModel;

  @ManyToOne
  @JoinColumn(name = "id_situacao")
  private SituacaoModel situacaoModel;

  @Column(name = "descricao")
  private String descricao;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_cricao")
  private LocalDateTime dataCriacao;
}
