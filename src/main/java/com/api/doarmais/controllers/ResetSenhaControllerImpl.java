package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.ResetSenhaController;
import com.api.doarmais.dtos.request.ResetRequestDto;
import com.api.doarmais.dtos.request.TrocarSenhaRequestDto;
import com.api.doarmais.dtos.response.ResetSenhaResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.events.ResetCriadoEvent;
import com.api.doarmais.exceptions.*;
import com.api.doarmais.models.tabelas.ResetSenhaModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ResetSenhaService;
import com.api.doarmais.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResetSenhaControllerImpl implements ResetSenhaController {

  @Autowired private ResetSenhaService resetSenhaService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private ModelMapper modelMapper;

  public ResponseEntity<ResetSenhaResponseDto> enviarEmail(ResetRequestDto resetRequestDto) {

    if (!usuarioService.verificarUsuarioPorEmail(resetRequestDto.getEmail())) {
      throw new UserNotFound("Email inválido");
    }

    if (resetSenhaService.verificarPedidoPorEmail(resetRequestDto.getEmail())) {
      ResetSenhaModel resetSenhaModel = resetSenhaService.buscarPedido(resetRequestDto.getEmail());
      if (resetSenhaService.expirou(resetSenhaModel)) {
        resetSenhaModel.setId(SituacaoModel.TOKEN_EXPERIRADO);
        resetSenhaService.gravar(resetSenhaModel);
      } else {
        throw new ResetAlreadyExists("Um pedido de troca ainda está ativo");
      }
    }

    var pedidoGerado = resetSenhaService.gerarPedido(resetRequestDto.getEmail());
    resetSenhaService.gravar(pedidoGerado);
    eventPublisher.publishEvent(new ResetCriadoEvent(pedidoGerado));

    return new ResponseEntity<ResetSenhaResponseDto>(
        modelMapper.map(
            resetSenhaService.buscarPedidoCriado(pedidoGerado).get(), ResetSenhaResponseDto.class),
        HttpStatus.CREATED);
  }

  public ResponseEntity<UsuarioResponseDto> trocarSenha(
      String token, TrocarSenhaRequestDto trocarSenhaRequestDto) {
    ResetSenhaModel resetSenhaModel = resetSenhaService.buscarPedidoPorToken(token);
    if (!resetSenhaService.verificarPedidoPorToken(token))
      throw new TokenDoesNotExists("URL inválida");

    if (resetSenhaService.expirou(resetSenhaModel))
      throw new ResetExpired("Seu pedido de alteração de senha já expirou");

    if (resetSenhaModel.getIdSituacao().equals(SituacaoModel.TOKEN_UTILIZADO))
      throw new LinkAlreadyUsed("Esse link já foi utilizado para alterar a senha");

    if (!trocarSenhaRequestDto.getNovaSenha().equals(trocarSenhaRequestDto.getConfirmaSenha()))
      throw new PasswordNotEqual("As senhas devem ser iguais");

    resetSenhaModel.setIdSituacao(SituacaoModel.TOKEN_UTILIZADO);
    resetSenhaService.gravar(resetSenhaModel);
    UsuarioModel usuarioModel =
        usuarioService.buscarUsuarioPorEmail(resetSenhaModel.getEmailUsuario());

    usuarioModel.setSenha(passwordEncoder.encode(trocarSenhaRequestDto.getNovaSenha()));
    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }
}
