package com.api.doarmais.dtos.response;

import lombok.Data;

@Data
public class EnderecoResponseDto {

  private String cep;
  private String uf;
  private String bairro;
  private String cidade;
  private String logradouro;
  private Integer numero;
  private String complemento;
}
