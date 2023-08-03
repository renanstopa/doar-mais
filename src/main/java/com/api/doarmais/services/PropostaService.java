package com.api.doarmais.services;

import com.api.doarmais.dtos.request.ItemPropostaRequestDto;
import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.dtos.response.ItemPropostaResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.repositories.ItemAnuncioPropostaRepository;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import com.api.doarmais.repositories.PropostaRepository;
import com.api.doarmais.repositories.ResetSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class PropostaService {

  @Autowired private PropostaRepository propostaRepository;

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  @Autowired private ItemAnuncioPropostaRepository itemAnuncioPropostaRepository;

  public PropostaModel gerarProposta(PropostaRequestDto request, AnuncioModel anuncio) {
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
}
