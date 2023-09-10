package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaPropostaViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaPropostaConfirmadaViewRepository
    extends JpaRepository<ConsultaPropostaViewModel, Integer> {}
