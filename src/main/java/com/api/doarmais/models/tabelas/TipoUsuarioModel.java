package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipo_usuario")
public class TipoUsuarioModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  private String descricao;
}
