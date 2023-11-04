package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaGerenciarMelhoriaBugViewModel;
import com.api.doarmais.repositories.ConsultaGerenciarMelhoriaBugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaGerenciarMelhoriaBugViewService {

  @Autowired private ConsultaGerenciarMelhoriaBugRepository consultaGerenciarMelhoriaBugRepository;

  public ConsultaGerenciarMelhoriaBugViewModel consultar(Integer id) {
    return consultaGerenciarMelhoriaBugRepository.findById(id).get();
  }
}
