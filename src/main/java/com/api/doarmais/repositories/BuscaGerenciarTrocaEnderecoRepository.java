package com.api.doarmais.repositories;

import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuscaGerenciarTrocaEnderecoRepository
    extends JpaRepository<BuscaGerenciarTrocaEnderecoViewModel, Integer> {

  @Query("select t from BuscaGerenciarTrocaEnderecoViewModel t where t.idSituacao = 61")
  List<BuscaGerenciarTrocaEnderecoViewModel> buscarSolicitacoesQuery();
}
