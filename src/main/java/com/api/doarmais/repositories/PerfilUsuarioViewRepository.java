package com.api.doarmais.repositories;

import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioViewRepository
    extends JpaRepository<PerfilUsuarioViewModel, Integer> {}
