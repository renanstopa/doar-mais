package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.TrocaEnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrocaEnderecoRepository extends JpaRepository<TrocaEnderecoModel, Integer> {}
