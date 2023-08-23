package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.AnuncioController;
import com.api.doarmais.dtos.request.*;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.DenunciaResponseDto;
import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.events.PropostaCriadaEvent;
import com.api.doarmais.exceptions.EndDateBeforeBeginDate;
import com.api.doarmais.exceptions.ImpossibleItemQuantity;
import com.api.doarmais.exceptions.InvalidDate;
import com.api.doarmais.exceptions.WrongDate;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import com.api.doarmais.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doacaorapida")
public class DoacaoRapidaControllerImpl implements AnuncioController {

  @Autowired private AnuncioService anuncioService;
  @Autowired private ItemAnuncioService itemAnuncioService;
  @Autowired private TipoAnuncioService tipoAnuncioService;
  @Autowired private EnderecoService enderecoService;
  @Autowired private ConsultaAnuncioViewService consultaAnuncioViewService;
  @Autowired private PropostaService propostaService;
  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;
  @Autowired private ApplicationEventPublisher eventPublisher;
  @Autowired private ModelMapper modelMapper;
  @Autowired private DenunciaService denunciaService;

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
    anuncioService.completarInformacoes(anuncioModel, 3);

    var usuarioCriador =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    anuncioModel.setUsuarioModel(usuarioCriador);

    anuncioModel = anuncioService.gravar(anuncioModel);

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

  public ResponseEntity<List<BuscaAnuncioViewModel>> buscar(
      String titulo, String cidade, Integer tipoUsuario, Integer tipoCategoriaItem) {
    var usuarioLogado =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (cidade == null || cidade.isBlank()) {
      var enderecoAtivo = enderecoService.buscarEnderecoAtivo(usuarioLogado.getId());
      cidade = enderecoAtivo.getCidade();
    }

    FiltroAnuncioRequestDto filtro =
        new FiltroAnuncioRequestDto(titulo, cidade, tipoUsuario, 1, tipoCategoriaItem, usuarioLogado.getId());

    return new ResponseEntity<List<BuscaAnuncioViewModel>>(
        anuncioService.buscar(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaAnuncioViewModel> consultar(Integer id) {
    ConsultaAnuncioViewModel consultaAnuncioViewModel = consultaAnuncioViewService.consultar(id);
    List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(id);
    consultaAnuncioViewModel.armazenarItens(listaItens);

    return new ResponseEntity<ConsultaAnuncioViewModel>(consultaAnuncioViewModel, HttpStatus.OK);
  }

  public ResponseEntity<PropostaResponseDto> criarProposta(PropostaRequestDto propostaRequestDto) {
    if(!anuncioService.verificarQuantidadeItem(propostaRequestDto))
      throw new ImpossibleItemQuantity("Quantidade escolhida deve ser menor que a restante do item");

    if(!anuncioService.verificarDataAgendada(propostaRequestDto))
      throw new WrongDate("Data da proposta deve estar entre as datas estipuladas pelo criador do anúncio");

    AnuncioModel anuncioModel = anuncioService.buscarPorId(propostaRequestDto.getIdAnuncio());
    PropostaModel propostaModel = propostaService.gerarProposta(propostaRequestDto, anuncioModel);
    itemAnuncioPropostaService.gerarItensProposta(propostaModel, propostaRequestDto);
    anuncioService.atualizarSituacao(anuncioModel);
    eventPublisher.publishEvent(new PropostaCriadaEvent(propostaModel));

    return new ResponseEntity<PropostaResponseDto>(propostaService.gerarResponse(propostaModel), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<DenunciaResponseDto> denunciarAnuncio(DenunciaRequestDto denunciaRequestDto) {
    var denunciaModel = new DenunciaModel();
    BeanUtils.copyProperties(denunciaRequestDto, denunciaModel);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_CRIADA));
    denunciaModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    var usuarioLogado = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    denunciaModel.setUsuarioModel(usuarioLogado);

    return new ResponseEntity<DenunciaResponseDto>(
            modelMapper.map(denunciaService.gravar(denunciaModel), DenunciaResponseDto.class),
            HttpStatus.CREATED);
  }

}
