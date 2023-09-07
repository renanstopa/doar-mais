package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
  boolean existsByCepAndNumeroAndUsuarioModelId(String cep, Integer numero, Integer usuario);

  @Query("select count(t) from EnderecoModel t where t.usuarioModel.id = ?1")
  Integer verificarQtdEnderecoQuery(Integer idUsuario);

  EnderecoModel findByUsuarioModelId(Integer idUsuario);
}
