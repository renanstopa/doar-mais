package com.api.doarmais.dtos.request;

import lombok.Data;

@Data
public class FiltroAnuncioRequestDto {

    private String titulo;

    private String cidade;

    private Integer tipoUsuario;

    private Integer tipoAnuncio;

    private Integer tipoCategoriaItem;

}
