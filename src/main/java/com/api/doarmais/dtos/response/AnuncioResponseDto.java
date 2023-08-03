package com.api.doarmais.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class AnuncioResponseDto {

  private Integer id;

  private String titulo;

  private List<ItemAnuncioResponseDto> itens;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
  private LocalDateTime dataInicioDisponibilidade;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
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
