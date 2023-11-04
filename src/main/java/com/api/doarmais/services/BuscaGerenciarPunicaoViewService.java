package com.api.doarmais.services;

import com.api.doarmais.models.views.BuscaGerenciarPunicaoViewModel;
import com.api.doarmais.repositories.BuscaGerenciarPunicaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaGerenciarPunicaoViewService {

  @Autowired private BuscaGerenciarPunicaoRepository buscaGerenciarPunicaoRepository;

  public List<BuscaGerenciarPunicaoViewModel> buscar() {
    return buscaGerenciarPunicaoRepository.buscarQuery();
  }
}
