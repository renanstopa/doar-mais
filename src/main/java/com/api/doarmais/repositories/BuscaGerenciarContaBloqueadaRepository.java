package com.api.doarmais.repositories;

import com.api.doarmais.models.views.BuscaGerenciarContaBloqueadaViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuscaGerenciarContaBloqueadaRepository
    extends JpaRepository<BuscaGerenciarContaBloqueadaViewModel, Integer> {}
