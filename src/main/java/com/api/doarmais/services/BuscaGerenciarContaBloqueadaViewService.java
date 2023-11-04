package com.api.doarmais.services;

import com.api.doarmais.models.views.BuscaGerenciarContaBloqueadaViewModel;
import com.api.doarmais.repositories.BuscaGerenciarContaBloqueadaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaGerenciarContaBloqueadaViewService {

  @Autowired private BuscaGerenciarContaBloqueadaRepository buscaGerenciarContaBloqueadaRepository;

  public List<BuscaGerenciarContaBloqueadaViewModel> buscar() {
    return buscaGerenciarContaBloqueadaRepository.findAll();
  }
}
