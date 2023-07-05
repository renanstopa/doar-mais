package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "situacao")
public class SituacaoModel {

  public static final Integer TOKEN_NAO_UTILIZADO = 1;
  public static final Integer TOKEN_EXPERIRADO = 2;
  public static final Integer TOKEN_UTILIZADO = 3;

  public static final Integer CONTA_SEM_EMAIL_VERIFICADO = 11;
  public static final Integer CONTA_SEM_APROVACAO_DO_ADM = 12;
  public static final Integer CONTA_APTA_PARA_USO = 13;
  public static final Integer CONTA_SUSPENSA = 14;
  public static final Integer CONTA_BLOQUEADA = 15;

  public static final Integer ANUNCIO_CRIADO = 21;
  public static final Integer ANUNCIO_ITENS_ESGOTADOS = 22;
  public static final Integer ANUNCIO_CANCELADO = 23;
  public static final Integer ANUNCIO_FINALIZADO = 24;

  public static final Integer PROPOSTA_CONFIRMADA = 31;
  public static final Integer PROPOSTA_CANCELADA = 32;
  public static final Integer ENCONTRO_REALIZADO = 33;
  public static final Integer ENCONTRO_NAO_REALIZADO = 34;

  public static final Integer DENUNCIA_CRIADA = 41;
  public static final Integer DENUNCIA_GERENCIADA = 42;

  @Id
  @NonNull
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  private String descricao;
}
