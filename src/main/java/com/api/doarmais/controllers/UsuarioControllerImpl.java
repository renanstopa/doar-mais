package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.UsuarioController;
import com.api.doarmais.dtos.request.AtualizarDadosRequestDto;
import com.api.doarmais.dtos.request.EnderecoRequestDto;
import com.api.doarmais.dtos.request.TrocarSenhaLogadoRequestDto;
import com.api.doarmais.dtos.response.EnderecoResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.exceptions.PasswordNotEqual;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.services.PerfilUsuarioViewService;
import com.api.doarmais.services.TrocaEnderecoService;
import com.api.doarmais.services.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioControllerImpl implements UsuarioController {

  @Autowired private UsuarioService usuarioService;

  @Autowired private PerfilUsuarioViewService perfilUsuarioViewService;

  @Autowired private TrocaEnderecoService trocaEnderecoService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private ModelMapper modelMapper;

  public ResponseEntity<PerfilUsuarioViewModel> perfilUsuario() {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return new ResponseEntity<PerfilUsuarioViewModel>(
        perfilUsuarioViewService.consultarPerfil(usuarioModel.getId()).get(), HttpStatus.FOUND);
  }

  public ResponseEntity<UsuarioResponseDto> atualizarDados(AtualizarDadosRequestDto atualizarDadosRequestDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    BeanUtils.copyProperties(atualizarDadosRequestDto, usuarioModel);

    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }

  public ResponseEntity<EnderecoResponseDto> solicitarTrocaEndereco(EnderecoRequestDto enderecoRequestDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return new ResponseEntity<EnderecoResponseDto>(
        modelMapper.map(
            trocaEnderecoService.criarSolicitacao(enderecoRequestDto, usuarioModel),
            EnderecoResponseDto.class),
        HttpStatus.CREATED);
  }

  public ResponseEntity<UsuarioResponseDto> trocarSenha(TrocarSenhaLogadoRequestDto trocarSenhaLogadoRequestDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!usuarioService.verificarSenhaAtual(
        trocarSenhaLogadoRequestDto.getSenhaAtual(), passwordEncoder, usuarioModel))
      throw new PasswordNotEqual("Senha atual não corresponde a registrada em seu usuário");

    if (!trocarSenhaLogadoRequestDto
        .getNovaSenha()
        .equals(trocarSenhaLogadoRequestDto.getConfirmaSenha()))
      throw new PasswordNotEqual("As senhas devem ser iguais");

    usuarioModel.setSenha(passwordEncoder.encode(trocarSenhaLogadoRequestDto.getNovaSenha()));

    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }
}
