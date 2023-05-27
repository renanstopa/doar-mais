package com.api.doarmais.repositories;

import com.api.doarmais.models.TipoUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioModel, Integer> {
    TipoUsuarioModel findByCdTipoUsuario(Integer cdTipoUsuario);
}
