package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropostaConfirmadaCanceladaEvent {

  private PropostaModel propostaModel;
  private UsuarioModel usuarioModel;
  private String motivo;
}
