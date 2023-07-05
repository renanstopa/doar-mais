package com.api.doarmais.events;

import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioCriadoEvent {

  private AutenticacaoEmailModel autenticacaoEmailModel;
}
