package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ResetSenhaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetSenhaRepository extends JpaRepository<ResetSenhaModel, Integer> {

  boolean existsByEmailUsuarioAndIdSituacao(String email, Integer situacao);

  @Query("select r from ResetSenhaModel r where r.emailUsuario = ?1 order by r.id desc limit 1")
  ResetSenhaModel buscarUltimoPedidoQuery(String email);

  ResetSenhaModel findByToken(String token);

  boolean existsByToken(String token);
}
