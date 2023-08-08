package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.PropostaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropostaCanceladaAnuncioEvent {

  private PropostaModel propostaModel;
  private String motivo;
}
