package com.api.doarmais.services;

import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.dtos.response.ItemPropostaResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.events.PropostaCanceladaEdicaoAnuncioEvent;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropostaService {

  @Autowired private PropostaRepository propostaRepository;

  @Autowired private AnuncioRepository anuncioRepository;

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  @Autowired private ItemAnuncioPropostaRepository itemAnuncioPropostaRepository;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public PropostaModel gerarProposta(PropostaRequestDto request, AnuncioModel anuncio) {
    anuncio.setQuantidadeProposta(anuncio.getQuantidadeProposta() + 1);
    anuncioRepository.save(anuncio);

    PropostaModel propostaModel = new PropostaModel();
    propostaModel.setDataAgendada(request.getDataAgendada());
    propostaModel.setAnuncioModel(anuncio);

    var usuarioCriador =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    propostaModel.setUsuarioModel(usuarioCriador);
    propostaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.PROPOSTA_ENVIADA));
    return propostaRepository.save(propostaModel);
  }

    public PropostaResponseDto gerarResponse(PropostaModel proposta) {
      PropostaResponseDto response = new PropostaResponseDto();

      response.setId(proposta.getId());
      response.setDataAgendada(proposta.getDataAgendada());
      response.setItemList(new ArrayList<>());

      List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList = itemAnuncioPropostaRepository.findByPropostaModelId(proposta.getId());
      for (ItemAnuncioPropostaModel item : itemAnuncioPropostaModelList) {
        ItemPropostaResponseDto itemPropostaResponseDto = new ItemPropostaResponseDto();
        itemPropostaResponseDto.setIdItem(item.getItemAnuncioModel().getId());
        itemPropostaResponseDto.setQuantidade(item.getQuantidadeSolicitada());
        response.getItemList().add(itemPropostaResponseDto);
      }

      return response;
    }

  public void cancelar(PropostaModel proposta) {
    proposta.setSituacaoModel(new SituacaoModel(SituacaoModel.PROPOSTA_CANCELADA));
    propostaRepository.save(proposta);
  }

  public List<PropostaModel> cancelarTodasPropostasDoAnuncio(Integer id) {
    AnuncioModel anuncioModel = anuncioRepository.findById(id).get();
    List<PropostaModel> propostaModelList = propostaRepository.buscarPorAnuncioIdQuery(id);

    for (PropostaModel proposta : propostaModelList)
      eventPublisher.publishEvent(new PropostaCanceladaEdicaoAnuncioEvent(proposta));

    propostaRepository.cancelarTodasPropostaAnuncioQuery(id);
    return propostaModelList;
  }

  public List<PropostaModel> cancelarPropostaPorItem(Integer id) {
    List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList = itemAnuncioPropostaRepository.buscarPorItemAnuncioIdQuery(id);
    List<PropostaModel> propostaModelList = new ArrayList<>();

    for (ItemAnuncioPropostaModel itemProposta : itemAnuncioPropostaModelList) {
      PropostaModel propostaModel = propostaRepository.findById(itemProposta.getPropostaModel().getId()).get();
      propostaModelList.add(propostaModel);

      propostaRepository.propostaEmAnaliseQuery(propostaModel.getId());

      eventPublisher.publishEvent(new PropostaCanceladaEdicaoAnuncioEvent(propostaModel));

    }

    return propostaModelList;
  }

  public void cancelarPropostasEmAnalise() {
    List<PropostaModel> propostaModelList = propostaRepository.findBySituacaoModelId(35);
    for (PropostaModel proposta : propostaModelList) {
      AnuncioModel anuncioModel = proposta.getAnuncioModel();
      anuncioModel.setQuantidadeProposta(anuncioModel.getQuantidadeProposta() - 1);
      anuncioRepository.save(anuncioModel);
    }

    propostaRepository.cancelarPropostaQuery();
  }
}
