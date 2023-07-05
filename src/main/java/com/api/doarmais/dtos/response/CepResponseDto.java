package com.api.doarmais.dtos.response;

import lombok.Data;

@Data
public class CepResponseDto {

  private String cep;
  private String city;
  private String state;
  private String neighborhood;
  private String street;
}
