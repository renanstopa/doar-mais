package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.repositories.SituacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SituacaoService {

  @Autowired private SituacaoRepository situacaoRepository;

  public boolean verificarExistencia() {
    return situacaoRepository.existsById(1);
  }

  public void saveAllSituacoes(List<SituacaoModel> situacaoModels) {
    situacaoRepository.saveAll(situacaoModels);
  }
}
