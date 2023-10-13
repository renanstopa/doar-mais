package com.api.doarmais.services;

import com.api.doarmais.models.views.ConsultaGerenciarPunicaoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarUsuarioDenunciadoViewModel;
import com.api.doarmais.repositories.ConsultaGerenciarPunicaoRepository;
import com.api.doarmais.repositories.ConsultaGerenciarUsuarioDenunciadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaGerenciarUsuarioDenunciadoViewService {

  @Autowired private ConsultaGerenciarUsuarioDenunciadoRepository consultaGerenciarUsuarioDenunciadoRepository;

    public ConsultaGerenciarUsuarioDenunciadoViewModel consultar(Integer id) {
      return consultaGerenciarUsuarioDenunciadoRepository.findById(id).get();
    }
}
