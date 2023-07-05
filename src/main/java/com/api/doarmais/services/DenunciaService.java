package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.DenunciaModel;
import com.api.doarmais.repositories.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaService {

  @Autowired private DenunciaRepository denunciaRepository;

  public DenunciaModel gravar(DenunciaModel denunciaModel) {
    return denunciaRepository.save(denunciaModel);
  }
}
