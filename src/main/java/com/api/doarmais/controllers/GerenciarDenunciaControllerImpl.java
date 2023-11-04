package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.GerenciarDenunciaController;
import com.api.doarmais.dtos.request.FiltroGerenciarDenunciaRequestDto;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.*;
import com.api.doarmais.services.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GerenciarDenunciaControllerImpl implements GerenciarDenunciaController {

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private BuscaGerenciarDenunciaViewService buscaGerenciarDenunciaViewService;

  @Autowired
  private ConsultaGerenciarUsuarioDenunciadoViewService
      consultaGerenciarUsuarioDenunciadoViewService;

  @Autowired
  private ConsultaGerenciarAnuncioDenunciadoViewService
      consultaGerenciarAnuncioDenunciadoViewService;

  @Autowired
  private ConsultaGerenciarMelhoriaBugViewService consultaGerenciarMelhoriaBugViewService;

  @Autowired private DenunciaService denunciaService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private AnuncioService anuncioService;

  @Autowired private PropostaService propostaService;

  public ResponseEntity<List<BuscaGerenciarDenunciaViewModel>> buscar(Integer tipoDenuncia) {
    FiltroGerenciarDenunciaRequestDto filtro = new FiltroGerenciarDenunciaRequestDto(tipoDenuncia);

    return new ResponseEntity<List<BuscaGerenciarDenunciaViewModel>>(
        buscaGerenciarDenunciaViewService.buscar(filtro), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> consultarDenunciaUsuario(
      Integer id) {
    return new ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel>(
        consultaGerenciarUsuarioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> consultarDenunciaAnuncio(
      Integer id) {
    return new ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel>(
        consultaGerenciarAnuncioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel> consultarMelhoriaBug(Integer id) {
    return new ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel>(
        consultaGerenciarMelhoriaBugViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> punirUsuario(Integer id) {
    DenunciaModel denunciaModel = denunciaService.consultar(id);
    UsuarioModel usuarioModel = denunciaModel.getUsuarioModelDenunciado();

    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_GERENCIADA));
    denunciaService.gravar(denunciaModel);

    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_BLOQUEADA));
    usuarioService.gravar(usuarioModel);

    return new ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel>(
        consultaGerenciarUsuarioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> verificarDenunciaUsuario(
      Integer id) {
    DenunciaModel denunciaModel = denunciaService.consultar(id);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_GERENCIADA));
    denunciaService.gravar(denunciaModel);

    return new ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel>(
        consultaGerenciarUsuarioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> suspenderAnuncio(Integer id) {
    DenunciaModel denunciaModel = denunciaService.consultar(id);
    AnuncioModel anuncioModel = denunciaModel.getAnuncioModel();

    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_GERENCIADA));
    denunciaService.gravar(denunciaModel);

    anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CANCELADO));
    anuncioService.gravar(anuncioModel);
    propostaService.cancelarTodasPropostasDoAnuncio(
        anuncioModel.getId(), "O anúncio aprensetava conteúdo impróprio.");

    return new ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel>(
        consultaGerenciarAnuncioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> verificarDenunciaAnuncio(
      Integer id) {
    DenunciaModel denunciaModel = denunciaService.consultar(id);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_GERENCIADA));
    denunciaService.gravar(denunciaModel);

    return new ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel>(
        consultaGerenciarAnuncioDenunciadoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel> verificarMelhoriaBug(Integer id) {
    DenunciaModel denunciaModel = denunciaService.consultar(id);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_GERENCIADA));
    denunciaService.gravar(denunciaModel);

    return new ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel>(
        consultaGerenciarMelhoriaBugViewService.consultar(id), HttpStatus.OK);
  }
}
