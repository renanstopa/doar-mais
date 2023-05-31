package com.api.doarmais.repositories;

import com.api.doarmais.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    UsuarioModel findByTxEmail(String email);
    boolean existsByTxEmail(String email);
    boolean existsByTxDocumento(String documento);

}
