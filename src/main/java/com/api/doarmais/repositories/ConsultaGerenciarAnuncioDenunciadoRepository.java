package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaGerenciarAnuncioDenunciadoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarAnuncioDenunciadoRepository
    extends JpaRepository<ConsultaGerenciarAnuncioDenunciadoViewModel, Integer> {}
