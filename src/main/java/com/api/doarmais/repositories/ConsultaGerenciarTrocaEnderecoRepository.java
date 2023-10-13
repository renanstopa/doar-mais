package com.api.doarmais.repositories;

import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.services.ConsultaGerenciarTrocaEnderecoViewService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarTrocaEnderecoRepository extends JpaRepository<ConsultaGerenciarTrocaEnderecoViewModel, Integer> {

}
