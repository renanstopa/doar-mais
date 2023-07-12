package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.TipoAnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAnuncioRepository extends JpaRepository<TipoAnuncioModel, Integer> {
}
