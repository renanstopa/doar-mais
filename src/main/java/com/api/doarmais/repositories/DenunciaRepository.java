package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.DenunciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepository extends JpaRepository<DenunciaModel, Integer> {}
