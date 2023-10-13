package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.GerenciarTrocaEnderecoController;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TrocaEnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class GerenciarTrocaEnderecoControllerImpl implements GerenciarTrocaEnderecoController {

  @Autowired private BuscaGerenciarTrocaEnderecoViewService buscaGerenciarTrocaEnderecoViewService;

  @Autowired private ConsultaGerenciarTrocaEnderecoViewService consultaGerenciarTrocaEnderecoViewService;

  @Autowired private TrocaEnderecoService trocaEnderecoService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private EnderecoService enderecoService;

  public ResponseEntity<List<BuscaGerenciarTrocaEnderecoViewModel>> buscar() {
    return new ResponseEntity<List<BuscaGerenciarTrocaEnderecoViewModel>>(buscaGerenciarTrocaEnderecoViewService.buscar(), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> consultar(Integer id) {
    return new ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel>(consultaGerenciarTrocaEnderecoViewService.consultar(id), HttpStatus.OK);
  }

  public HttpEntity<InputStreamResource> download(Integer id) {
    try {
      TrocaEnderecoModel trocaEnderecoModel = trocaEnderecoService.consultar(id);

      String pathDir = trocaEnderecoModel.getCaminhoArquivo();

      File destinationFile = new File(pathDir);
      InputStreamResource resource = new InputStreamResource(new FileInputStream(destinationFile));

      return ResponseEntity.ok()
              .header("Content-Disposition", "attacghment;filename=" + trocaEnderecoModel.getArquivo())
              .contentLength(destinationFile.length())
              .body(resource);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> aceitar(Integer id) {
    TrocaEnderecoModel trocaEnderecoModel = trocaEnderecoService.consultar(id);
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(trocaEnderecoModel.getIdUsuario()).get();
    EnderecoModel enderecoModel = enderecoService.buscarEndereco(usuarioModel.getId());

    BeanUtils.copyProperties(trocaEnderecoModel, enderecoModel);
    enderecoService.gravar(enderecoModel);

    usuarioModel.setArquivo(trocaEnderecoModel.getArquivo());
    usuarioModel.setCaminhoArquivo(trocaEnderecoModel.getCaminhoArquivo());
    usuarioService.gravar(usuarioModel);

    trocaEnderecoModel.setIdSituacao(SituacaoModel.SOLICITACAO_ANALISADA);
    trocaEnderecoService.gravar(trocaEnderecoModel);

    return new ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel>(consultaGerenciarTrocaEnderecoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> recusar(Integer id) {
    TrocaEnderecoModel trocaEnderecoModel = trocaEnderecoService.consultar(id);
    trocaEnderecoModel.setIdSituacao(SituacaoModel.SOLICITACAO_ANALISADA);
    trocaEnderecoService.gravar(trocaEnderecoModel);

    return new ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel>(consultaGerenciarTrocaEnderecoViewService.consultar(id), HttpStatus.OK);
  }
}
