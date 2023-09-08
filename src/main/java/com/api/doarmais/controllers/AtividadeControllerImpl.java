package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.AtividadeController;
import com.api.doarmais.dtos.request.*;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.dtos.response.ItemPropostaResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.exceptions.EndDateBeforeBeginDate;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.BuscaPropostasAgendadasViewModel;
import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaPropostaConfirmadaViewModel;
import com.api.doarmais.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AtividadeControllerImpl implements AtividadeController {

  @Autowired private AnuncioService anuncioService;

  @Autowired private ItemAnuncioService itemAnuncioService;

  @Autowired private PropostaService propostaService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @Autowired private ConsultaAnuncioViewService consultaAnuncioViewService;

  @Autowired private ConsultaPropostaConfirmadaViewService consultaPropostaConfirmadaViewService;

  @Autowired private AtividadeService atividadeService;

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private ModelMapper modelMapper;

  //ENDPOINTS UTILIZADOS NA ABA DE ANÚNCIOS

  public ResponseEntity<List<BuscaAnuncioViewModel>> buscarAnuncios(String titulo, String cidade, Integer tipoUsuario, Integer tipoCategoriaItem) {
    var usuarioLogado =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
            new FiltroAnuncioRequestDto(titulo, cidade, tipoUsuario, null, tipoCategoriaItem, usuarioLogado.getId());

    return new ResponseEntity<List<BuscaAnuncioViewModel>>(atividadeService.buscarAnuncios(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaAnuncioViewModel> consultarAnuncio(Integer id) {
    ConsultaAnuncioViewModel consultaAnuncioViewModel = consultaAnuncioViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaAnuncioViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaAnuncioViewModel>(consultaAnuncioViewModel, HttpStatus.OK);
  }

  public ResponseEntity<AnuncioResponseDto> editarAnuncio(Integer id, EditarAnuncioRequestDto editarAnuncioRequestDto) {
    if (editarAnuncioRequestDto
            .getDataFimDisponibilidade()
            .isBefore(editarAnuncioRequestDto.getDataInicioDisponibilidade()))
      throw new EndDateBeforeBeginDate("Data final da disponibilidade deve ser depois da inicial");

    var anuncioModel = anuncioService.buscarPorId(id);
    List<PropostaModel> propostasCanceladas = new ArrayList<>();
    boolean trocouInfoPrincipal = anuncioModel.verficarTrocaInfoPrincipal(editarAnuncioRequestDto);
    if (trocouInfoPrincipal)
      propostasCanceladas = propostaService.cancelarTodasPropostasDoAnuncio(anuncioModel.getId(), "A pessoa que criou o anúncio precisou editá-lo!");

    BeanUtils.copyProperties(editarAnuncioRequestDto, anuncioModel);
    anuncioService.completarInformacoes(anuncioModel, anuncioModel.getTipoAnuncioModel().getId());
    anuncioModel = anuncioService.gravar(anuncioModel);

    for (EditarItemAnuncioRequestDto itemDto : editarAnuncioRequestDto.getListaItens()) {
      var itemAnuncioModel = itemAnuncioService.buscarPorId(itemDto.getId());

      if (!trocouInfoPrincipal && itemAnuncioModel.verificarTrocaitem(itemDto))
        propostasCanceladas = propostaService.cancelarPropostaPorItem(itemAnuncioModel.getId());

      if(!propostasCanceladas.isEmpty() && !itemAnuncioModel.verificarTrocaitem(itemDto))
        anuncioService.voltarQuantidadeOriginalItem(itemAnuncioModel, propostasCanceladas, itemDto);

      BeanUtils.copyProperties(itemDto, itemAnuncioModel);
      itemAnuncioService.gravar(itemAnuncioModel);
    }

    propostaService.cancelarPropostasEmAnalise();

    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(anuncioModel.getId());
    List<ItemAnuncioResponseDto> listaItensResponse = new ArrayList<ItemAnuncioResponseDto>();
    for (ItemAnuncioModel item : listaItens) {
      listaItensResponse.add(modelMapper.map(item, ItemAnuncioResponseDto.class));
    }

    AnuncioResponseDto response = modelMapper.map(anuncioModel, AnuncioResponseDto.class);
    response.setItens(listaItensResponse);

    return new ResponseEntity<AnuncioResponseDto>(response, HttpStatus.OK);
  }

  public ResponseEntity<AnuncioResponseDto> cancelarAnuncio(Integer id, MotivoCancelamentoDto motivoCancelamentoDto) {
    var anuncioModel = anuncioService.buscarPorId(id);

    List<PropostaModel> propostasCanceladas = new ArrayList<>();
    propostasCanceladas = propostaService.cancelarTodasPropostasDoAnuncio(anuncioModel.getId(), "Anúncio foi cancelado!");
    propostaService.cancelarPropostasEmAnalise();

    anuncioService.verificarEnvioPunicao(propostasCanceladas, motivoCancelamentoDto);
    anuncioService.cancelarAnuncio(anuncioModel);

    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(anuncioModel.getId());
    List<ItemAnuncioResponseDto> listaItensResponse = new ArrayList<ItemAnuncioResponseDto>();
    for (ItemAnuncioModel item : listaItens) {
      listaItensResponse.add(modelMapper.map(item, ItemAnuncioResponseDto.class));
    }

    AnuncioResponseDto response = modelMapper.map(anuncioModel, AnuncioResponseDto.class);
    response.setItens(listaItensResponse);

    return new ResponseEntity<AnuncioResponseDto>(response, HttpStatus.OK);
  }

  //ENDPOINTS UTILIZADOS NA ABA DE CONFIRMAÇÕES
  //ENDPOINTS UTILIZADOS NA ABA DE AGENDADOS

  public ResponseEntity<List<BuscaPropostasAgendadasViewModel>> buscarAgendados(String titulo, String cidade, Integer tipoUsuario, Integer tipoCategoriaItem) {
    var usuarioLogado =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
            new FiltroAnuncioRequestDto(titulo, cidade, tipoUsuario, null, tipoCategoriaItem, usuarioLogado.getId());

    return new ResponseEntity<List<BuscaPropostasAgendadasViewModel>>(atividadeService.buscarAgendados(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaConfirmadaViewModel> consultarAgendado(Integer id) {
    ConsultaPropostaConfirmadaViewModel consultaPropostasConfirmadasViewModel = consultaPropostaConfirmadaViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaPropostasConfirmadasViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaPropostaConfirmadaViewModel>(consultaPropostasConfirmadasViewModel, HttpStatus.OK);
  }

  public ResponseEntity<PropostaResponseDto> cancelarAgendado(Integer id, MotivoCancelamentoDto motivoCancelamentoDto){
    UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    PropostaModel propostaModel = propostaService.consultar(id);
    propostaService.cancelar(propostaModel, (usuarioModel.getEmail().equals(propostaModel.getUsuarioModel().getEmail()) ? propostaModel.getAnuncioModel().getUsuarioModel() : propostaModel.getUsuarioModel()), motivoCancelamentoDto);
    propostaService.verificarPunicaoCancelamento(propostaModel, motivoCancelamentoDto, (usuarioModel.getEmail().equals(propostaModel.getUsuarioModel().getEmail()) ? propostaModel.getAnuncioModel().getUsuarioModel() : propostaModel.getUsuarioModel()));

    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(propostaModel.getAnuncioModel().getId());
    List<ItemPropostaResponseDto> listaItensResponse = new ArrayList<ItemPropostaResponseDto>();
    for (ItemAnuncioModel item : listaItens) {
      listaItensResponse.add(modelMapper.map(item, ItemPropostaResponseDto.class));
    }

    PropostaResponseDto response = modelMapper.map(propostaModel, PropostaResponseDto.class);
    response.setItemList(listaItensResponse);

    return new ResponseEntity<PropostaResponseDto>(response, HttpStatus.OK);
  }

  //ENDPOINTS UTILIZADOS NA ABA DE HISTÓRICO
}
