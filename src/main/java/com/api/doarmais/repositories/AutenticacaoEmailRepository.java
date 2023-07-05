package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacaoEmailRepository
    extends JpaRepository<AutenticacaoEmailModel, Integer> {

  boolean existsByToken(String token);

  List<AutenticacaoEmailModel> findByToken(String token);
}
