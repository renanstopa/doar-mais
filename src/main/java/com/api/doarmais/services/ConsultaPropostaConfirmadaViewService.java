package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaPropostaConfirmadaViewModel;
import com.api.doarmais.repositories.ConsultaAnuncioViewRepository;
import com.api.doarmais.repositories.ConsultaPropostaConfirmadaViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaPropostaConfirmadaViewService {

  @Autowired private ConsultaPropostaConfirmadaViewRepository consultaPropostaConfirmadaViewRepository;

  public ConsultaPropostaConfirmadaViewModel consultar(Integer id) {
    return consultaPropostaConfirmadaViewRepository.findById(id).get();
  }
}
