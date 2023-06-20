package com.api.doarmais.repositories;

import com.api.doarmais.models.AutenticacaoEmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutenticacaoEmailRepository extends JpaRepository<AutenticacaoEmailModel, Integer> {

    boolean existsByTxToken(String token);

    List<AutenticacaoEmailModel> findByTxToken(String token);
}
