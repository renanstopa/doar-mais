package com.api.doarmais.services;

import com.api.doarmais.dtos.request.FiltroGerenciarContaRequestDto;
import com.api.doarmais.models.views.BuscaGerenciarContaBloqueadaViewModel;
import com.api.doarmais.models.views.BuscaGerenciarContasViewModel;
import com.api.doarmais.repositories.BuscaGerenciarContaBloqueadaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscaGerenciarContaBloqueadaViewService {

  @Autowired private BuscaGerenciarContaBloqueadaRepository buscaGerenciarContaBloqueadaRepository;

  public List<BuscaGerenciarContaBloqueadaViewModel> buscar() {
    return buscaGerenciarContaBloqueadaRepository.findAll();
  }
}
