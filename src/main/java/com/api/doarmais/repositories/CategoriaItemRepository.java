package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaItemRepository extends JpaRepository<CategoriaItemModel, Integer> {}
