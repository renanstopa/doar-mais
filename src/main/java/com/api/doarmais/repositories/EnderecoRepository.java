package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
    boolean existsByTxCepAndCdNumeroAndUsuarioModelCdUsuario(String cep, Integer numero, Integer usuario);

    EnderecoModel findByUsuarioModelCdUsuarioAndCkAtivo(Integer usuario, int ativo);
}
