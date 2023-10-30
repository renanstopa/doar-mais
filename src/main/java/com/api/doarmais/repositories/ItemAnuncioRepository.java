package com.api.doarmais.repositories;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAnuncioRepository extends JpaRepository<ItemAnuncioModel, Integer> {
  @Query("select t from ItemAnuncioModel t where t.anuncioModel.id = ?1 and t.situacaoModel.id = 71")
  List<ItemAnuncioModel> buscarItensAtivosAnuncioQuery(Integer id);
}
