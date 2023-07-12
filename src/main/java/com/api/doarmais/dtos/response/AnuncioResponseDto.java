package com.api.doarmais.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AnuncioResponseDto {

    private String titulo;

    private List<ItemAnuncioResponseDto> itens;

    private LocalDateTime dataInicioDisponibilidade;

    private LocalDateTime dataFimDisponibilidade;

    private String cep;

    private String uf;

    private String cidade;

    private String bairro;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String pontoReferencia;

}
