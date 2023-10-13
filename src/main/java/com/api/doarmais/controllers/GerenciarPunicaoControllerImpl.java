package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.GerenciarPunicaoController;
import com.api.doarmais.events.ContaSuspensaEvent;
import com.api.doarmais.models.tabelas.PunicaoModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.BuscaGerenciarPunicaoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarPunicaoViewModel;
import com.api.doarmais.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GerenciarPunicaoControllerImpl implements GerenciarPunicaoController {

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private BuscaGerenciarPunicaoViewService buscaGerenciarPunicaoViewService;

  @Autowired private ConsultaGerenciarPunicaoViewService consultaGerenciarPunicaoViewService;

  @Autowired private PunicaoService punicaoService;

  @Autowired private UsuarioService usuarioService;

  public ResponseEntity<List<BuscaGerenciarPunicaoViewModel>> buscar() {
    return new ResponseEntity<List<BuscaGerenciarPunicaoViewModel>>(buscaGerenciarPunicaoViewService.buscar(), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> consultar(Integer id) {
    return new ResponseEntity<ConsultaGerenciarPunicaoViewModel>(consultaGerenciarPunicaoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> punir(Integer id) {
    PunicaoModel punicaoModel = punicaoService.consultar(id);
    UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorId(punicaoModel.getIdUsuario()).get();

    punicaoModel.setIdSituacao(SituacaoModel.PUNICAO_VERIFICADA);
    punicaoService.gravar(punicaoModel);

    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_BLOQUEADA));
    usuarioService.gravar(usuarioModel);

    eventPublisher.publishEvent(new ContaSuspensaEvent(usuarioModel));

    return new ResponseEntity<ConsultaGerenciarPunicaoViewModel>(consultaGerenciarPunicaoViewService.consultar(id), HttpStatus.OK);
  }

  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> naopunir(Integer id) {
    PunicaoModel punicaoModel = punicaoService.consultar(id);
    punicaoModel.setIdSituacao(SituacaoModel.PUNICAO_VERIFICADA);
    punicaoService.gravar(punicaoModel);

    return new ResponseEntity<ConsultaGerenciarPunicaoViewModel>(consultaGerenciarPunicaoViewService.consultar(id), HttpStatus.OK);
  }
}
