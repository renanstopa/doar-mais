package com.api.doarmais.dtos;

import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoDto {
    @NotNull
    private UsuarioModel usuarioModel;

    @NotBlank
    @Size(min = 9, max = 9)
    private String txCep;

    @NotNull
    private Integer cdNumero;

    @NotBlank
    @Size(max = 100)
    private String txComplemento;
}
