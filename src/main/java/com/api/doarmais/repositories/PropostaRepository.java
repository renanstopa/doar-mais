package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.PropostaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaModel, Integer> {
  @Query(
      "select t from PropostaModel t where t.anuncioModel.id = ?1 and t.situacaoModel.id in (31, 32)")
  List<PropostaModel> buscarPorAnuncioIdQuery(Integer id);

  @Transactional
  @Modifying
  @Query(
      "update PropostaModel set situacaoModel.id = 35 where anuncioModel.id = ?1 and situacaoModel.id in (31, 32)")
  void cancelarTodasPropostaAnuncioQuery(Integer id);

  @Transactional
  @Modifying
  @Query("update PropostaModel set situacaoModel.id = 34 where situacaoModel.id = 35")
  void cancelarPropostaQuery();

  @Transactional
  @Modifying()
  @Query("update PropostaModel set situacaoModel.id = 35 where id = ?1")
  void propostaEmAnaliseQuery(Integer id);

  List<PropostaModel> findBySituacaoModelId(int i);
}
