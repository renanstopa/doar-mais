package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.PropostaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropostaRecusadaEvent {

  private PropostaModel propostaModel;
}
