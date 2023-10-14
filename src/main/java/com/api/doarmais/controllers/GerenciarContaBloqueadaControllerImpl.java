package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.GerenciarContaBloqueadaController;
import com.api.doarmais.controllers.interfaces.GerenciarContaController;
import com.api.doarmais.dtos.request.FiltroGerenciarContaRequestDto;
import com.api.doarmais.events.ContaAceitaEvent;
import com.api.doarmais.events.ContaBanidaEvent;
import com.api.doarmais.events.ContaDesbloqueadaEvent;
import com.api.doarmais.events.ContaRecusadaEvent;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.*;
import com.api.doarmais.services.BuscaGerenciarContaBloqueadaViewService;
import com.api.doarmais.services.BuscaGerenciarContaViewService;
import com.api.doarmais.services.PerfilUsuarioViewService;
import com.api.doarmais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class GerenciarContaBloqueadaControllerImpl implements GerenciarContaBloqueadaController {

  @Autowired private BuscaGerenciarContaBloqueadaViewService buscaGerenciarContaBloqueadaViewService;

  @Autowired private PerfilUsuarioViewService perfilUsuarioViewService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public ResponseEntity<List<BuscaGerenciarContaBloqueadaViewModel>> buscar() {
    return new ResponseEntity<List<BuscaGerenciarContaBloqueadaViewModel>>(buscaGerenciarContaBloqueadaViewService.buscar(), HttpStatus.OK);
  }

  public ResponseEntity<PerfilUsuarioViewModel> consultar(Integer id) {
    return new ResponseEntity<PerfilUsuarioViewModel>(perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }

  public ResponseEntity<PerfilUsuarioViewModel> desbloquear(Integer id) {
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(id).get();
    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_APTA_PARA_USO));
    usuarioService.gravar(usuarioModel);

    eventPublisher.publishEvent(new ContaDesbloqueadaEvent(usuarioModel));

    return new ResponseEntity<PerfilUsuarioViewModel>(perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }

  public ResponseEntity<PerfilUsuarioViewModel> banir(Integer id) {
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(id).get();
    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_BANIDA));
    usuarioService.gravar(usuarioModel);

    eventPublisher.publishEvent(new ContaBanidaEvent(usuarioModel));

    return new ResponseEntity<PerfilUsuarioViewModel>(perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }

}
