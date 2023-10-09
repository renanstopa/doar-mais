package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.AuthController;
import com.api.doarmais.dtos.request.CriarUsuarioRequestDto;
import com.api.doarmais.dtos.request.RequestDto;
import com.api.doarmais.dtos.response.ResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.events.OngCriadaEvent;
import com.api.doarmais.events.UsuarioCriadoEvent;
import com.api.doarmais.exceptions.InvalidDocument;
import com.api.doarmais.exceptions.UserAlreadyExists;
import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.AutenticacaoEmailService;
import com.api.doarmais.services.AuthenticationService;
import com.api.doarmais.services.EnderecoService;
import com.api.doarmais.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

  @Autowired private AuthenticationService authenticationService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private EnderecoService enderecoService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private AutenticacaoEmailService autenticacaoEmailService;

  @Autowired private ModelMapper modelMapper;

  @Autowired private ApplicationEventPublisher eventPublisher;

  public ResponseEntity<UsuarioResponseDto> registrarUsuario(
      CriarUsuarioRequestDto criarUsuarioRequestDto) {

    if (usuarioService.verificarUsuarioPorEmail(criarUsuarioRequestDto.getEmail()))
      throw new UserAlreadyExists("Email já cadastrado");

    if (usuarioService.verificarUsuarioPorDocumento(criarUsuarioRequestDto.getDocumento()))
      throw new UserAlreadyExists("CPF já cadastrado");

    if (!usuarioService.validarCPF(criarUsuarioRequestDto.getDocumento()))
      throw new InvalidDocument("CPF inválido");

    var usuarioModel = new UsuarioModel();
    BeanUtils.copyProperties(criarUsuarioRequestDto, usuarioModel);
    usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 1);
    usuarioService.armazenarDocumento(criarUsuarioRequestDto.getComprovante(), usuarioModel);
    usuarioModel = usuarioService.gravar(usuarioModel);

    var enderecoModel = new EnderecoModel();
    enderecoService.armazenarEndereco(usuarioModel, enderecoModel, criarUsuarioRequestDto);
    enderecoService.gravar(enderecoModel);

    AutenticacaoEmailModel autenticacaoGerada =
        autenticacaoEmailService.gerarAutenticacaoEmail(usuarioModel.getEmail());
    autenticacaoEmailService.gravar(autenticacaoGerada);
    eventPublisher.publishEvent(new UsuarioCriadoEvent(autenticacaoGerada));

    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioModel, UsuarioResponseDto.class), HttpStatus.CREATED);
  }

  public ResponseEntity<UsuarioResponseDto> registrarOng(
      CriarUsuarioRequestDto criarUsuarioRequestDto) {

    if (usuarioService.verificarUsuarioPorEmail(criarUsuarioRequestDto.getEmail()))
      throw new UserAlreadyExists("Email já cadastrado");

    if (usuarioService.verificarUsuarioPorDocumento(criarUsuarioRequestDto.getDocumento()))
      throw new UserAlreadyExists("CNPJ já cadastrado");

    var usuarioModel = new UsuarioModel();
    BeanUtils.copyProperties(criarUsuarioRequestDto, usuarioModel);
    usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 2);
    usuarioService.armazenarDocumento(criarUsuarioRequestDto.getComprovante(), usuarioModel);
    usuarioModel = usuarioService.gravar(usuarioModel);

    var enderecoModel = new EnderecoModel();
    enderecoService.armazenarEndereco(usuarioModel, enderecoModel, criarUsuarioRequestDto);
    enderecoService.gravar(enderecoModel);

    AutenticacaoEmailModel autenticacaoGerada =
        autenticacaoEmailService.gerarAutenticacaoEmail(usuarioModel.getEmail());
    autenticacaoEmailService.gravar(autenticacaoGerada);
    eventPublisher.publishEvent(new OngCriadaEvent(autenticacaoGerada));

    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioModel, UsuarioResponseDto.class), HttpStatus.CREATED);
  }

  public ResponseEntity<ResponseDto> authenticate(RequestDto request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
