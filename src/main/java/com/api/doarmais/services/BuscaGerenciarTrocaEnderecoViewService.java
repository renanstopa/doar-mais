package com.api.doarmais.services;

import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.repositories.BuscaGerenciarTrocaEnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaGerenciarTrocaEnderecoViewService {

  @Autowired private BuscaGerenciarTrocaEnderecoRepository buscaGerenciarTrocaEnderecoRepository;

  public List<BuscaGerenciarTrocaEnderecoViewModel> buscar() {
    return buscaGerenciarTrocaEnderecoRepository.buscarSolicitacoesQuery();
  }
}
