package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.SituacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository extends JpaRepository<SituacaoModel, Integer> {

}
