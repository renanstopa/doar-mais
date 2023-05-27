package com.api.doarmais.dtos;

import lombok.Data;

@Data
public class CepDto {

    private String cep;
    private String city;
    private String state;
    private String neighborhood;
    private String street;

}
