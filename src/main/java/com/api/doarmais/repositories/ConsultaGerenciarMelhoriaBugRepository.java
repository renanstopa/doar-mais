package com.api.doarmais.repositories;

import com.api.doarmais.models.views.ConsultaGerenciarAnuncioDenunciadoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarMelhoriaBugViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaGerenciarMelhoriaBugRepository extends JpaRepository<ConsultaGerenciarMelhoriaBugViewModel, Integer> {

}
