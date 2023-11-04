package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemAnuncioService {

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  public void gravar(ItemAnuncioModel itemAnuncioModel) {
    itemAnuncioRepository.save(itemAnuncioModel);
  }

  public List<ItemAnuncioModel> buscaPorAnuncio(Integer id) {
    return itemAnuncioRepository.buscarItensAtivosAnuncioQuery(id);
  }

  public ItemAnuncioModel buscarPorId(Integer id) {
    return itemAnuncioRepository.findById(id).get();
  }

  public void deletarItem(ItemAnuncioModel itemAnuncioModel) {
    itemAnuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ITEM_INATIVO));
    itemAnuncioRepository.save(itemAnuncioModel);
  }
}
