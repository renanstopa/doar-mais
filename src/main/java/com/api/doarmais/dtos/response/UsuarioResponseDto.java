package com.api.doarmais.dtos.response;

import lombok.Data;

@Data
public class UsuarioResponseDto {

  private String nome;
  private String email;
  private String telefone;
  private String documento;
}
