package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.AtividadeController;
import com.api.doarmais.dtos.request.*;
import com.api.doarmais.dtos.response.*;
import com.api.doarmais.events.PropostaConfirmadaEvent;
import com.api.doarmais.events.PropostaRecusadaEvent;
import com.api.doarmais.exceptions.CantConfirmProposta;
import com.api.doarmais.exceptions.EndDateBeforeBeginDate;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.*;
import com.api.doarmais.services.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtividadeControllerImpl implements AtividadeController {

  @Autowired private AnuncioService anuncioService;

  @Autowired private ItemAnuncioService itemAnuncioService;

  @Autowired private PropostaService propostaService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @Autowired private ConsultaAnuncioViewService consultaAnuncioViewService;

  @Autowired private ConsultaPropostaViewService consultaPropostaViewService;

  @Autowired private AtividadeService atividadeService;

  @Autowired private DenunciaService denunciaService;

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private ModelMapper modelMapper;

  // ENDPOINTS UTILIZADOS NA ABA DE ANÚNCIOS

  public ResponseEntity<List<BuscaAnuncioViewModel>> buscarAnuncios(
      String titulo,
      String cidade,
      Integer tipoUsuario,
      Integer tipoAnuncio,
      Integer tipoCategoriaItem) {
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(
            titulo,
            cidade,
            tipoUsuario,
            tipoAnuncio,
            tipoCategoriaItem,
            usuarioLogado.getId(),
            null);

    return new ResponseEntity<List<BuscaAnuncioViewModel>>(
        atividadeService.buscarAnuncios(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaAnuncioViewModel> consultarAnuncio(Integer id) {
    ConsultaAnuncioViewModel consultaAnuncioViewModel = consultaAnuncioViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaAnuncioViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaAnuncioViewModel>(consultaAnuncioViewModel, HttpStatus.OK);
  }

  public ResponseEntity<AnuncioResponseDto> editarAnuncio(
      Integer id, EditarAnuncioRequestDto editarAnuncioRequestDto) {
    if (editarAnuncioRequestDto
        .getDataFimDisponibilidade()
        .isBefore(editarAnuncioRequestDto.getDataInicioDisponibilidade()))
      throw new EndDateBeforeBeginDate("Data final da disponibilidade deve ser depois da inicial");

    var anuncioModel = anuncioService.buscarPorId(id);
    List<PropostaModel> propostasCanceladas = new ArrayList<>();
    boolean trocouInfoPrincipal = anuncioModel.verficarTrocaInfoPrincipal(editarAnuncioRequestDto);
    if (trocouInfoPrincipal)
      propostasCanceladas =
          propostaService.cancelarTodasPropostasDoAnuncio(
              anuncioModel.getId(), "A pessoa que criou o anúncio precisou editá-lo!");

    BeanUtils.copyProperties(editarAnuncioRequestDto, anuncioModel);
    anuncioService.completarInformacoes(anuncioModel, anuncioModel.getTipoAnuncioModel().getId());
    anuncioModel = anuncioService.gravar(anuncioModel);

    for (EditarItemAnuncioRequestDto itemDto : editarAnuncioRequestDto.getListaItens()) {
      var itemAnuncioModel = itemAnuncioService.buscarPorId(itemDto.getId());

      if (!trocouInfoPrincipal && itemAnuncioModel.verificarTrocaitem(itemDto))
        propostasCanceladas = propostaService.cancelarPropostaPorItem(itemAnuncioModel.getId());

      if (!propostasCanceladas.isEmpty() && !itemAnuncioModel.verificarTrocaitem(itemDto))
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

  public ResponseEntity<AnuncioResponseDto> cancelarAnuncio(
      Integer id, MotivoCancelamentoDto motivoCancelamentoDto) {
    var anuncioModel = anuncioService.buscarPorId(id);

    List<PropostaModel> propostasCanceladas = new ArrayList<>();
    propostasCanceladas =
        propostaService.cancelarTodasPropostasDoAnuncio(
            anuncioModel.getId(), "Anúncio foi cancelado!");
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

  // ENDPOINTS UTILIZADOS NA ABA DE CONFIRMAÇÕES

  public ResponseEntity<List<BuscaPropostasPendentesViewModel>> buscarPendentes(
      String titulo, String cidade, Integer tipoAnuncio, Integer tipoCategoriaItem) {
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(
            titulo, cidade, null, tipoAnuncio, tipoCategoriaItem, usuarioLogado.getId(), null);

    return new ResponseEntity<List<BuscaPropostasPendentesViewModel>>(
        atividadeService.buscarPendentes(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> consultarPendente(Integer id) {
    ConsultaPropostaViewModel consultaPropostaViewModel = consultaPropostaViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaPropostaViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaPropostaViewModel>(consultaPropostaViewModel, HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> confirmarPropostaPendente(Integer id) {
    PropostaModel propostaModel = propostaService.consultar(id);
    propostaService.confirmarProposta(propostaModel);
    eventPublisher.publishEvent(new PropostaConfirmadaEvent(propostaModel));

    return new ResponseEntity<ConsultaPropostaViewModel>(
        consultaPropostaViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> recusarPropostaPendente(Integer id) {
    PropostaModel propostaModel = propostaService.consultar(id);
    propostaService.recusarProposta(propostaModel);
    eventPublisher.publishEvent(new PropostaRecusadaEvent(propostaModel));

    anuncioService.voltarQuantidadeOriginalItem(propostaModel);

    return new ResponseEntity<ConsultaPropostaViewModel>(
        consultaPropostaViewService.consultar(id), HttpStatus.OK);
  }

  // ENDPOINTS UTILIZADOS NA ABA DE AGENDADOS

  public ResponseEntity<List<BuscaPropostasAgendadasViewModel>> buscarAgendados(
      String titulo,
      String cidade,
      Integer tipoUsuario,
      Integer tipoAnuncio,
      Integer tipoCategoriaItem) {
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(
            titulo,
            cidade,
            tipoUsuario,
            tipoAnuncio,
            tipoCategoriaItem,
            usuarioLogado.getId(),
            null);

    return new ResponseEntity<List<BuscaPropostasAgendadasViewModel>>(
        atividadeService.buscarAgendados(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> consultarAgendado(Integer id) {
    ConsultaPropostaViewModel consultaPropostaViewModel = consultaPropostaViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaPropostaViewModel.armazenarItens(listaItens);

    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    consultaPropostaViewModel.setPodeConfirmarEncontro(
        usuarioModel.getId().equals(consultaPropostaViewModel.getIdUsuarioAnuncio()) ? 1 : 2);

    return new ResponseEntity<ConsultaPropostaViewModel>(consultaPropostaViewModel, HttpStatus.OK);
  }

  public ResponseEntity<PropostaResponseDto> cancelarAgendado(
      Integer id, MotivoCancelamentoDto motivoCancelamentoDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    PropostaModel propostaModel = propostaService.consultar(id);
    propostaService.cancelar(
        propostaModel,
        (usuarioModel.getEmail().equals(propostaModel.getUsuarioModel().getEmail())
            ? propostaModel.getAnuncioModel().getUsuarioModel()
            : propostaModel.getUsuarioModel()),
        motivoCancelamentoDto);
    propostaService.verificarPunicaoCancelamento(
        propostaModel,
        motivoCancelamentoDto,
        (usuarioModel.getEmail().equals(propostaModel.getUsuarioModel().getEmail())
            ? propostaModel.getAnuncioModel().getUsuarioModel()
            : propostaModel.getUsuarioModel()));

    anuncioService.voltarQuantidadeOriginalItem(propostaModel);

    List<ItemAnuncioModel> listaItens =
        itemAnuncioService.buscaPorAnuncio(propostaModel.getAnuncioModel().getId());
    List<ItemPropostaResponseDto> listaItensResponse = new ArrayList<ItemPropostaResponseDto>();
    for (ItemAnuncioModel item : listaItens) {
      listaItensResponse.add(modelMapper.map(item, ItemPropostaResponseDto.class));
    }

    PropostaResponseDto response = modelMapper.map(propostaModel, PropostaResponseDto.class);
    response.setItemList(listaItensResponse);

    return new ResponseEntity<PropostaResponseDto>(response, HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> ocorreuEncontro(Integer idProposta) {
    PropostaModel propostaModel = propostaService.consultar(idProposta);

    if (propostaModel
        .getDataAgendada()
        .isAfter((LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))))
      throw new CantConfirmProposta(
          "A data agendada para a proposta ainda está anterior a data atual");

    propostaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ENCONTRO_REALIZADO));
    propostaService.gravarOcorrenciaEncontro(propostaModel);

    return new ResponseEntity<ConsultaPropostaViewModel>(
        consultaPropostaViewService.consultar(idProposta), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> naoOcorreuEncontro(Integer idProposta) {
    PropostaModel propostaModel = propostaService.consultar(idProposta);

    if (propostaModel
        .getDataAgendada()
        .isAfter((LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))))
      throw new CantConfirmProposta(
          "A data agendada para a proposta ainda está anterior a data atual");

    propostaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ENCONTRO_NAO_REALIZADO));
    propostaService.gravarOcorrenciaEncontro(propostaModel);

    return new ResponseEntity<ConsultaPropostaViewModel>(
        consultaPropostaViewService.consultar(idProposta), HttpStatus.OK);
  }

  // ENDPOINTS UTILIZADOS NA ABA DE HISTÓRICO

  public ResponseEntity<List<BuscaPropostasHistoricoViewModel>> buscarHistorico(
      String titulo,
      String cidade,
      Integer tipoUsuario,
      Integer tipoAnuncio,
      Integer tipoCategoriaItem,
      Integer situacao) {
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(
            titulo,
            cidade,
            tipoUsuario,
            tipoAnuncio,
            tipoCategoriaItem,
            usuarioLogado.getId(),
            situacao);

    return new ResponseEntity<List<BuscaPropostasHistoricoViewModel>>(
        atividadeService.buscarHistorico(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaPropostaViewModel> consultarHistorico(Integer id) {
    ConsultaPropostaViewModel consultaPropostaViewModel = consultaPropostaViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaPropostaViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaPropostaViewModel>(consultaPropostaViewModel, HttpStatus.OK);
  }

  public ResponseEntity<DenunciaResponseDto> denunciarHistorico(
      Integer id, DenunciaRequestDto denunciaRequestDto) {
    var denunciaModel = new DenunciaModel();
    BeanUtils.copyProperties(denunciaRequestDto, denunciaModel);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_CRIADA));
    denunciaModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    denunciaModel.setUsuarioModel(usuarioLogado);

    PropostaModel propostaModel = propostaService.consultar(id);
    denunciaModel.setUsuarioModelDenunciado(
        propostaModel.getUsuarioModel().getEmail().equals(usuarioLogado.getEmail())
            ? propostaModel.getAnuncioModel().getUsuarioModel()
            : propostaModel.getUsuarioModel());

    return new ResponseEntity<DenunciaResponseDto>(
        modelMapper.map(denunciaService.gravar(denunciaModel), DenunciaResponseDto.class),
        HttpStatus.CREATED);
  }
}
