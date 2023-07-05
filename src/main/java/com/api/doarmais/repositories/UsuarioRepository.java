package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
  UsuarioModel findByEmail(String email);

  boolean existsByEmail(String email);

  boolean existsByDocumento(String documento);
}
