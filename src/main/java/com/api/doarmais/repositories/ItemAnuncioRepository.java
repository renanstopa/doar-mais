package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import java.util.List;

import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAnuncioRepository extends JpaRepository<ItemAnuncioModel, Integer> {
  List<ItemAnuncioModel> findByAnuncioModelId(Integer id);

}
