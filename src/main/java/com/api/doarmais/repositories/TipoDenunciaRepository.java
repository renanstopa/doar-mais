package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.TipoDenunciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDenunciaRepository extends JpaRepository<TipoDenunciaModel, Integer> {}
