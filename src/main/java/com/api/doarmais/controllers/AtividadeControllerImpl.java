package com.api.doarmais.controllers;

import com.api.doarmais.clients.BrasilApiClient;
import com.api.doarmais.controllers.interfaces.AtividadeController;
import com.api.doarmais.controllers.interfaces.BrasilApiController;
import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.EditarAnuncioRequestDto;
import com.api.doarmais.dtos.request.EditarItemAnuncioRequestDto;
import com.api.doarmais.dtos.request.ItemAnuncioRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.CepResponseDto;
import com.api.doarmais.dtos.response.CnpjResponseDto;
import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.exceptions.EndDateBeforeBeginDate;
import com.api.doarmais.exceptions.InvalidDate;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.services.AnuncioService;
import com.api.doarmais.services.ItemAnuncioPropostaService;
import com.api.doarmais.services.ItemAnuncioService;
import com.api.doarmais.services.PropostaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AtividadeControllerImpl implements AtividadeController {

  @Autowired private AnuncioService anuncioService;

  @Autowired private ItemAnuncioService itemAnuncioService;

  @Autowired private PropostaService propostaService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private ModelMapper modelMapper;

  public ResponseEntity<AnuncioResponseDto> editarAnuncio(Integer id, EditarAnuncioRequestDto editarAnuncioRequestDto) {
    if (editarAnuncioRequestDto
            .getDataInicioDisponibilidade()
            .isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))))
      throw new InvalidDate("Data inicial da disponibilidade não pode ser antes que a data atual");

    if (editarAnuncioRequestDto
            .getDataFimDisponibilidade()
            .isBefore(editarAnuncioRequestDto.getDataInicioDisponibilidade()))
      throw new EndDateBeforeBeginDate("Data final da disponibilidade deve ser depois da inicial");

    Boolean editouItem = false;
    var anuncioModel = anuncioService.buscarPorId(id);
    BeanUtils.copyProperties(editarAnuncioRequestDto, anuncioModel);
    anuncioService.completarInformacoes(anuncioModel, anuncioModel.getTipoAnuncioModel().getId());

    var usuarioCriador =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    anuncioModel.setUsuarioModel(usuarioCriador);

    anuncioModel = anuncioService.gravar(anuncioModel);

    for (EditarItemAnuncioRequestDto itemDto : editarAnuncioRequestDto.getListaItens()) {
      var itemAnuncioModel = itemAnuncioService.buscarPorId(itemDto.getId());
      if(!editouItem)
        editouItem = itemAnuncioModel.verificarTrocaitem(itemDto);

      BeanUtils.copyProperties(itemDto, itemAnuncioModel);
      itemAnuncioService.gravar(itemAnuncioModel);
    }

    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(anuncioModel.getId());
    List<ItemAnuncioResponseDto> listaItensResponse = new ArrayList<ItemAnuncioResponseDto>();
    for (ItemAnuncioModel item : listaItens) {
      listaItensResponse.add(modelMapper.map(item, ItemAnuncioResponseDto.class));
    }
    AnuncioResponseDto response = modelMapper.map(anuncioModel, AnuncioResponseDto.class);
    response.setItens(listaItensResponse);

    List<PropostaModel> propostaModelList = propostaService.buscarPorAnuncio(id);
    for (PropostaModel proposta : propostaModelList) {
      propostaService.cancelar(proposta);
      itemAnuncioPropostaService.adicionarQuantidadeitem(proposta, editouItem);
    }

    return new ResponseEntity<AnuncioResponseDto>(response, HttpStatus.OK);
  }
}
