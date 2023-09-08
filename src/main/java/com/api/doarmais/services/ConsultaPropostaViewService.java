package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaPropostaViewModel;
import com.api.doarmais.repositories.ConsultaPropostaConfirmadaViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaPropostaViewService {

  @Autowired private ConsultaPropostaConfirmadaViewRepository consultaPropostaViewRepository;

  public ConsultaPropostaViewModel consultar(Integer id) {
    return consultaPropostaViewRepository.findById(id).get();
  }
}
