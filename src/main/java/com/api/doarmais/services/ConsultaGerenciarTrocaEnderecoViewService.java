package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.repositories.ConsultaGerenciarTrocaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaGerenciarTrocaEnderecoViewService {

  @Autowired
  private ConsultaGerenciarTrocaEnderecoRepository consultaGerenciarTrocaEnderecoRepository;

  public ConsultaGerenciarTrocaEnderecoViewModel consultar(Integer id) {
    return consultaGerenciarTrocaEnderecoRepository.findById(id).get();
  }
}
