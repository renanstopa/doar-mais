package com.api.doarmais.dtos.response;

import lombok.Data;

@Data
public class CnpjResponseDto {

  private String razao_social;
  private String ddd_telefone_1;
  private Integer cep;
  private String uf;
  private String bairro;
  private String numero;
  private String logradouro;
  private String complemento;
  private String municipio;
}
