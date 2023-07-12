package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAnuncioRepository extends JpaRepository<ItemAnuncioModel, Integer> {
}
