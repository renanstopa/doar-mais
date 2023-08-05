package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAnuncioPropostaRepository extends JpaRepository<ItemAnuncioPropostaModel, Integer> {
    List<ItemAnuncioPropostaModel> findByPropostaModelId(Integer id);

    @Query("select t from ItemAnuncioPropostaModel t where t.itemAnuncioModel.id = ?1 and t.propostaModel.situacaoModel.id in (31, 32, 35)")
    List<ItemAnuncioPropostaModel> buscarPorItemAnuncioIdQuery(Integer id);

}
