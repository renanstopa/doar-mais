package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaGerenciarPunicaoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarPunicaoRepository
    extends JpaRepository<ConsultaGerenciarPunicaoViewModel, Integer> {}
