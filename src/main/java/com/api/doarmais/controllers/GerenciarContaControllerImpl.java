package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.GerenciarContaController;
import com.api.doarmais.dtos.request.*;
import com.api.doarmais.events.ContaAceitaEvent;
import com.api.doarmais.events.ContaRecusadaEvent;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.BuscaGerenciarContasViewModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.services.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GerenciarContaControllerImpl implements GerenciarContaController {

  @Autowired private BuscaGerenciarContaViewService buscaGerenciarContaViewService;

  @Autowired private PerfilUsuarioViewService perfilUsuarioViewService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public ResponseEntity<List<BuscaGerenciarContasViewModel>> buscar(Integer tipoUsuario) {
    FiltroGerenciarContaRequestDto filtro = new FiltroGerenciarContaRequestDto(tipoUsuario);

    return new ResponseEntity<List<BuscaGerenciarContasViewModel>>(
        buscaGerenciarContaViewService.buscar(filtro), HttpStatus.OK);
  }

  public ResponseEntity<PerfilUsuarioViewModel> consultar(Integer id) {
    return new ResponseEntity<PerfilUsuarioViewModel>(
        perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }

  public @ResponseBody HttpEntity<InputStreamResource> download(Integer id) {
    try {
      UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(id).get();

      String pathDir = usuarioModel.getCaminhoArquivo();

      File destinationFile = new File(pathDir);
      InputStreamResource resource = new InputStreamResource(new FileInputStream(destinationFile));

      return ResponseEntity.ok()
          .header("Content-Disposition", "attacghment;filename=" + usuarioModel.getArquivo())
          .contentLength(destinationFile.length())
          .body(resource);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<PerfilUsuarioViewModel> aceitarConta(Integer id) {
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(id).get();
    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_APTA_PARA_USO));
    usuarioService.gravar(usuarioModel);
    eventPublisher.publishEvent(new ContaAceitaEvent(usuarioModel));

    return new ResponseEntity<PerfilUsuarioViewModel>(
        perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }

  public ResponseEntity<PerfilUsuarioViewModel> recusarConta(Integer id) {
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(id).get();
    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_SEM_APROVACAO_DO_ADM));
    usuarioService.gravar(usuarioModel);
    eventPublisher.publishEvent(new ContaRecusadaEvent(usuarioModel));

    return new ResponseEntity<PerfilUsuarioViewModel>(
        perfilUsuarioViewService.consultarPerfil(id).get(), HttpStatus.OK);
  }
}
