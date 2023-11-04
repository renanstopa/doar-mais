package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaGerenciarUsuarioDenunciadoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarUsuarioDenunciadoRepository
    extends JpaRepository<ConsultaGerenciarUsuarioDenunciadoViewModel, Integer> {}
