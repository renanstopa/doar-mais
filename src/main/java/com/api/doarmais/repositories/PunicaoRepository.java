package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.PunicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunicaoRepository extends JpaRepository<PunicaoModel, Integer> {}
