package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import com.api.doarmais.repositories.ConsultaAnuncioViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaAnuncioViewService {

  @Autowired private ConsultaAnuncioViewRepository consultaAnuncioViewRepository;

  public ConsultaAnuncioViewModel consultar(Integer id) {
    return consultaAnuncioViewRepository.findById(id).get();
  }
}
