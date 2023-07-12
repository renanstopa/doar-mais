package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAnuncioRepository extends JpaRepository<ItemAnuncioModel, Integer> {
    List<ItemAnuncioModel> findByAnuncioModelId(Integer id);
}
