package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAnuncioPropostaRepository extends JpaRepository<ItemAnuncioPropostaModel, Integer> {
    List<ItemAnuncioPropostaModel> findByPropostaModelId(Integer id);
}
