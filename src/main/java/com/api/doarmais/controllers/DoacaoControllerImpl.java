package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.DoacaoController;
import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.FiltroAnuncioRequestDto;
import com.api.doarmais.dtos.request.ItemAnuncioRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.exceptions.EndDateBeforeBeginDate;
import com.api.doarmais.exceptions.InvalidDate;
import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import com.api.doarmais.services.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoacaoControllerImpl implements DoacaoController {
  @Autowired private DoacaoService doacaoService;

  @Autowired private ItemAnuncioService itemAnuncioService;

  @Autowired private TipoAnuncioService tipoAnuncioService;

  @Autowired private EnderecoService enderecoService;

  @Autowired private ConsultaAnuncioViewService consultaAnuncioViewService;

  @Autowired private ModelMapper modelMapper;

  // BUSCA DE ANÚNCIO COM FINALIDADE DE CRIAR UMA PROPOSTA

  public ResponseEntity<List<BuscaAnuncioViewModel>> buscar(
      String titulo, String cidade, Integer tipoUsuario, Integer tipoCategoriaItem) {
    if (cidade == null || cidade.isBlank()) {
      var usuarioLogado =
          (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      var enderecoAtivo = enderecoService.buscarEnderecoAtivo(usuarioLogado.getId());
      cidade = enderecoAtivo.getCidade();
    }

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(titulo, cidade, tipoUsuario, 1, tipoCategoriaItem);

    return new ResponseEntity<List<BuscaAnuncioViewModel>>(
        doacaoService.buscar(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaAnuncioViewModel> consultar(Integer id) {
    return new ResponseEntity<ConsultaAnuncioViewModel>(
        consultaAnuncioViewService.consultar(id), HttpStatus.OK);
  }

  // AÇÕES RELACIONADAS AOS SEUS ANÚNCIOS

  public ResponseEntity<AnuncioResponseDto> criarAnuncio(AnuncioRequestDto anuncioRequestDto) {
    if (anuncioRequestDto
        .getDataInicioDisponibilidade()
        .isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))))
      throw new InvalidDate("Data inicial da disponibilidade não pode ser antes que a data atual");

    if (anuncioRequestDto
        .getDataFimDisponibilidade()
        .isBefore(anuncioRequestDto.getDataInicioDisponibilidade()))
      throw new EndDateBeforeBeginDate("Data final da disponibilidade deve ser depois da inicial");

    var anuncioModel = new AnuncioModel();
    BeanUtils.copyProperties(anuncioRequestDto, anuncioModel);
    doacaoService.completarInformacoes(anuncioModel, 1);

    var usuarioCriador =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    anuncioModel.setUsuarioModel(usuarioCriador);

    anuncioModel = doacaoService.gravar(anuncioModel);

    for (ItemAnuncioRequestDto itemDto : anuncioRequestDto.getListaItens()) {
      var itemAnuncioModel = new ItemAnuncioModel();
      itemAnuncioModel.setAnuncioModel(anuncioModel);
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

    return new ResponseEntity<AnuncioResponseDto>(response, HttpStatus.CREATED);
  }
}
