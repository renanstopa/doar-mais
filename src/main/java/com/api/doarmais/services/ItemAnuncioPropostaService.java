package com.api.doarmais.services;

import com.api.doarmais.dtos.request.ItemPropostaRequestDto;
import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.repositories.ItemAnuncioPropostaRepository;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemAnuncioPropostaService {

  @Autowired private ItemAnuncioPropostaRepository itemAnuncioPropostaRepository;

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  public void gerarItensProposta(PropostaModel proposta, PropostaRequestDto request) {
    for (ItemPropostaRequestDto item : request.getItemPropostaRequestDtoList()) {
      ItemAnuncioModel itemAnuncioModel = itemAnuncioRepository.findById(item.getIdItem()).get();
      itemAnuncioModel.setQuantidade(itemAnuncioModel.getQuantidade() - item.getQuantidade());
      itemAnuncioRepository.save(itemAnuncioModel);

      ItemAnuncioPropostaModel itemAnuncioPropostaModel = new ItemAnuncioPropostaModel();
      itemAnuncioPropostaModel.setPropostaModel(proposta);
      itemAnuncioPropostaModel.setItemAnuncioModel(itemAnuncioModel);
      itemAnuncioPropostaModel.setQuantidadeSolicitada(item.getQuantidade());
      itemAnuncioPropostaRepository.save(itemAnuncioPropostaModel);
    }
  }

    public List<ItemAnuncioPropostaModel> buscarPorProposta(Integer id) {
      return itemAnuncioPropostaRepository.findByPropostaModelId(id);
    }
}
