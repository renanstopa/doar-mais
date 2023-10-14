package com.api.doarmais.repositories;

import com.api.doarmais.models.views.BuscaGerenciarContaBloqueadaViewModel;
import com.api.doarmais.models.views.BuscaGerenciarPunicaoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuscaGerenciarContaBloqueadaRepository extends JpaRepository<BuscaGerenciarContaBloqueadaViewModel, Integer> {

}
