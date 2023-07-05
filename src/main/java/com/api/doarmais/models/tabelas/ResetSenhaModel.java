package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reset_senha")
public class ResetSenhaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "email_usuario")
  private String emailUsuario;

  @Column(name = "id_situacao")
  private Integer idSituacao;

  @Column(name = "token")
  private String token;

  @Column(name = "data_validade")
  private LocalDateTime dataValidade;
}
