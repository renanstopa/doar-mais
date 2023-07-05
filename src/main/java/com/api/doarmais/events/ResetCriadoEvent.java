package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.ResetSenhaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResetCriadoEvent {

  private ResetSenhaModel pedidoGerado;
}
