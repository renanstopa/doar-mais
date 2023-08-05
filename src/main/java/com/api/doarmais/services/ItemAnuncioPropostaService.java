package com.api.doarmais.services;

import com.api.doarmais.dtos.request.ItemPropostaRequestDto;
import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.repositories.AnuncioRepository;
import com.api.doarmais.repositories.ItemAnuncioPropostaRepository;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemAnuncioPropostaService {

  @Autowired private ItemAnuncioPropostaRepository itemAnuncioPropostaRepository;

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  @Autowired private AnuncioRepository anuncioRepository;

  @Autowired private ApplicationEventPublisher eventPublisher;

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

    public void adicionarQuantidadeitem(PropostaModel proposta, Boolean editouItem) {
      AnuncioModel anuncio = anuncioRepository.findById(proposta.getAnuncioModel().getId()).get();
      List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList = itemAnuncioPropostaRepository.findByPropostaModelId(proposta.getId());

      if(!editouItem){
        if(anuncio.getSituacaoModel().getId().equals(22)){
          anuncio.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CRIADO));
          anuncioRepository.save(anuncio);
        }

        for (ItemAnuncioPropostaModel itemProposta : itemAnuncioPropostaModelList) {
          ItemAnuncioModel item = itemAnuncioRepository.findById(itemProposta.getItemAnuncioModel().getId()).get();
          item.setQuantidade(item.getQuantidade() + itemProposta.getQuantidadeSolicitada());
          itemAnuncioRepository.save(item);
        }
      }


    }
}
