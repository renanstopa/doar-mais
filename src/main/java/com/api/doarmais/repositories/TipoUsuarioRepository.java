package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.TipoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioModel, Integer> {}
