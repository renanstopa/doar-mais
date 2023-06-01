package com.api.doarmais.repositories;

import com.api.doarmais.models.ResetSenhaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetSenhaRepository extends JpaRepository<ResetSenhaModel, Integer> {

    boolean existsByTxEmailUsuarioAndCdSituacao(String email, Integer situacao);

    @Query("select r from ResetSenhaModel r where r.txEmailUsuario = ?1 order by r.cdResetSenha desc limit 1")
    ResetSenhaModel buscarUltimoPedidoQuery(String email);

    ResetSenhaModel findByTxToken(String token);

    boolean existsByTxToken(String token);
}
