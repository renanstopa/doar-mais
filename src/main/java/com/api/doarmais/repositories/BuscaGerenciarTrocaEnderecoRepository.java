package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuscaGerenciarTrocaEnderecoRepository extends JpaRepository<BuscaGerenciarTrocaEnderecoViewModel, Integer> {

    @Query("select t from BuscaGerenciarTrocaEnderecoViewModel t where t.idSituacao = 61")
    List<BuscaGerenciarTrocaEnderecoViewModel> buscarSolicitacoesQuery();
}
