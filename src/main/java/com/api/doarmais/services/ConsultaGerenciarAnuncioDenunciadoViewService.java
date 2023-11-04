package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaGerenciarAnuncioDenunciadoViewModel;
import com.api.doarmais.repositories.ConsultaGerenciarAnuncioDenunciadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaGerenciarAnuncioDenunciadoViewService {

  @Autowired
  private ConsultaGerenciarAnuncioDenunciadoRepository consultaGerenciarAnuncioDenunciadoRepository;

  public ConsultaGerenciarAnuncioDenunciadoViewModel consultar(Integer id) {
    return consultaGerenciarAnuncioDenunciadoRepository.findById(id).get();
  }
}
