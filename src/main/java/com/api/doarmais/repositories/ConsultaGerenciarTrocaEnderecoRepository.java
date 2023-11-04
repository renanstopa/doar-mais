package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarTrocaEnderecoRepository
    extends JpaRepository<ConsultaGerenciarTrocaEnderecoViewModel, Integer> {}
