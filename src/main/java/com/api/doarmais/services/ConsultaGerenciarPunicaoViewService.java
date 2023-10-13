package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaGerenciarPunicaoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.repositories.ConsultaGerenciarPunicaoRepository;
import com.api.doarmais.repositories.ConsultaGerenciarTrocaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaGerenciarPunicaoViewService {

  @Autowired private ConsultaGerenciarPunicaoRepository consultaGerenciarPunicaoRepository;

  public ConsultaGerenciarPunicaoViewModel consultar(Integer id) {
    return consultaGerenciarPunicaoRepository.findById(id).get();
  }
}
