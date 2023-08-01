package com.api.doarmais.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FiltroAnuncioRequestDto {

    String titulo;
    String cidade;
    Integer tipoUsuario;
    Integer tipoAnuncio;
    Integer tipoCategoriaItem;

}
