package com.api.doarmais.repositories;

import com.api.doarmais.models.views.BuscaGerenciarPunicaoViewModel;
import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuscaGerenciarPunicaoRepository extends JpaRepository<BuscaGerenciarPunicaoViewModel, Integer> {

    @Query("select t from BuscaGerenciarPunicaoViewModel t where t.idSituacao = 51")
    List<BuscaGerenciarPunicaoViewModel> buscarQuery();
}
