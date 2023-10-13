package com.api.doarmais.services;

import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.repositories.BuscaGerenciarTrocaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaGerenciarTrocaEnderecoViewService {

  @Autowired private BuscaGerenciarTrocaEnderecoRepository buscaGerenciarTrocaEnderecoRepository;

  public List<BuscaGerenciarTrocaEnderecoViewModel> buscar() {
    return buscaGerenciarTrocaEnderecoRepository.buscarSolicitacoesQuery();
  }
}
