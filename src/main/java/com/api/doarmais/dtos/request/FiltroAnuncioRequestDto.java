package com.api.doarmais.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FiltroAnuncioRequestDto {

  private String titulo;
  private String cidade;
  private Integer tipoUsuario;
  private Integer tipoAnuncio;
  private Integer tipoCategoriaItem;
  private Integer idUsuario;
}
