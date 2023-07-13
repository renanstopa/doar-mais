package com.api.doarmais.controllers;

import com.api.doarmais.controllers.interfaces.UsuarioController;
import com.api.doarmais.dtos.request.AtualizarDadosRequestDto;
import com.api.doarmais.dtos.request.EnderecoRequestDto;
import com.api.doarmais.dtos.request.TrocarSenhaRequestDto;
import com.api.doarmais.dtos.response.EnderecoResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.exceptions.AddressAlreadyExists;
import com.api.doarmais.exceptions.PasswordNotEqual;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.services.EnderecoService;
import com.api.doarmais.services.PerfilUsuarioViewService;
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

  @Autowired private EnderecoService enderecoService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private ModelMapper modelMapper;

  @GetMapping("/perfil")
  public ResponseEntity<PerfilUsuarioViewModel> perfilUsuario() {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return new ResponseEntity<PerfilUsuarioViewModel>(
        perfilUsuarioViewService.consultarPerfil(usuarioModel.getId()).get(), HttpStatus.FOUND);
  }

  @PatchMapping("/atualizardados")
  public ResponseEntity<UsuarioResponseDto> atualizarDados(
      @Valid @RequestBody AtualizarDadosRequestDto atualizarDadosRequestDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    BeanUtils.copyProperties(atualizarDadosRequestDto, usuarioModel);
    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }

  @PostMapping("/criarendereco")
  public ResponseEntity<EnderecoResponseDto> criarEndereco(
      @Valid @RequestBody EnderecoRequestDto enderecoRequestDto) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (enderecoService.verificarEnderecoExistente(
        enderecoRequestDto.getCep(), enderecoRequestDto.getNumero(), usuarioModel.getId())) {
      throw new AddressAlreadyExists("Endereço já cadastrado");
    }

    EnderecoModel enderecoModel = new EnderecoModel();
    BeanUtils.copyProperties(enderecoRequestDto, enderecoModel);
    enderecoModel.setAtivo(2);
    enderecoModel.setUsuarioModel(usuarioModel);

    return new ResponseEntity<EnderecoResponseDto>(
        modelMapper.map(enderecoService.gravar(enderecoModel), EnderecoResponseDto.class),
        HttpStatus.CREATED);
  }

  @PatchMapping("/trocarendereco/{endereco}")
  public ResponseEntity<EnderecoResponseDto> trocarEndereco(
      @PathVariable("endereco") Integer endereco) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    EnderecoModel enderecoAtual = enderecoService.buscarEnderecoAtivo(usuarioModel.getId());
    EnderecoModel novoEnderecoAtivo = enderecoService.buscarNovoEndereco(endereco).get();

    enderecoAtual.setAtivo(2);
    enderecoService.gravar(enderecoAtual);

    novoEnderecoAtivo.setAtivo(1);
    enderecoService.gravar(novoEnderecoAtivo);

    return new ResponseEntity<EnderecoResponseDto>(
        modelMapper.map(novoEnderecoAtivo, EnderecoResponseDto.class), HttpStatus.OK);
  }

  @PatchMapping("/trocarsenha")
  public ResponseEntity<UsuarioResponseDto> trocarSenha(
      @Valid @RequestBody TrocarSenhaRequestDto trocarSenhaRequestDto) {
    if (!trocarSenhaRequestDto.getSenha().equals(trocarSenhaRequestDto.getConfirmaSenha()))
      throw new PasswordNotEqual("As senhas devem ser iguais");

    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    usuarioModel.setSenha(passwordEncoder.encode(trocarSenhaRequestDto.getSenha()));

    return new ResponseEntity<UsuarioResponseDto>(
        modelMapper.map(usuarioService.gravar(usuarioModel), UsuarioResponseDto.class),
        HttpStatus.OK);
  }
}
