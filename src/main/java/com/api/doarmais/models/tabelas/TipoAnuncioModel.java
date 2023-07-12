package com.api.doarmais.models.tabelas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "tipo_anuncio")
public class TipoAnuncioModel {

  @Id
  @NonNull
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  private String descricao;
}
