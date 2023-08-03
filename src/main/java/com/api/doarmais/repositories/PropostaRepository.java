package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaModel, Integer> {
    List<PropostaModel> findByAnuncioModelIdAndSituacaoModelId(Integer id, int i);
}
