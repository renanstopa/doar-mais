package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContaAceitaEvent {

  private UsuarioModel usuarioModel;
}
