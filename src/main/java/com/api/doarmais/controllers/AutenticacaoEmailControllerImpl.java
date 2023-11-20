package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.AutenticacaoEmailController;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.exceptions.*;
import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.AutenticacaoEmailService;
import com.api.doarmais.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutenticacaoEmailControllerImpl implements AutenticacaoEmailController {

  @Autowired private AutenticacaoEmailService autenticacaoEmailService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private ModelMapper modelMapper;

  public ResponseEntity<UsuarioResponseDto> autenticarEmail(String token) {
    if (!autenticacaoEmailService.verificarPedidoPorToken(token))
      throw new TokenDoesNotExists("URL inválida");

    AutenticacaoEmailModel autenticacaoEmailModel = autenticacaoEmailService.buscarPorToken(token);
    if (autenticacaoEmailModel.getId().equals(SituacaoModel.TOKEN_UTILIZADO))
      throw new LinkAlreadyUsed("Esse link já foi utilizado para autenticar o email");

    autenticacaoEmailModel.setIdSituacao(SituacaoModel.TOKEN_UTILIZADO);
    autenticacaoEmailService.gravar(autenticacaoEmailModel);
    UsuarioModel usuarioModel =
        usuarioService.buscarUsuarioPorEmail(autenticacaoEmailModel.getEmailUsuario());

    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_SEM_APROVACAO_DO_ADM));
    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }
}
