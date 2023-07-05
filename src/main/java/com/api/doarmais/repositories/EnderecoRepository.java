package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
  boolean existsByCepAndNumeroAndUsuarioModelId(String cep, Integer numero, Integer usuario);

  EnderecoModel findByUsuarioModelIdAndAtivo(Integer usuario, int ativo);
}
