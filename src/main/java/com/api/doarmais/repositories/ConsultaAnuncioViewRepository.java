package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaAnuncioViewRepository
    extends JpaRepository<ConsultaAnuncioViewModel, Integer> {}
