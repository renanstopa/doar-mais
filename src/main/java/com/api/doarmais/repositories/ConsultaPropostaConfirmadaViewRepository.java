package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaPropostaConfirmadaViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaPropostaConfirmadaViewRepository extends JpaRepository<ConsultaPropostaConfirmadaViewModel, Integer> {}
