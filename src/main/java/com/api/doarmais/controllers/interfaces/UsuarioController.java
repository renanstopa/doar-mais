package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.AtualizarDadosRequestDto;
import com.api.doarmais.dtos.request.EnderecoRequestDto;
import com.api.doarmais.dtos.request.TrocarSenhaRequestDto;
import com.api.doarmais.dtos.response.EnderecoResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Usuário",
    description =
        "Endpoints utilizados para visualizar perfil, criar novo endereço, mudar endereço ativo e editar dados do usuário.")
@RequestMapping("/usuario")
public interface UsuarioController {

  @Operation(description = "Endpoint utilizado para visualizar o perfil.")
  @GetMapping("/perfil")
  public ResponseEntity<PerfilUsuarioViewModel> perfilUsuario();

  @Operation(description = "Endpoint utilizado para atualizar os dados.")
  @PatchMapping("/atualizardados")
  public ResponseEntity<UsuarioResponseDto> atualizarDados(
      @Valid @RequestBody AtualizarDadosRequestDto atualizarDadosRequestDto);

  @Operation(description = "Endpoint utilizado para criar um endereço.")
  @PostMapping("/criarendereco")
  public ResponseEntity<EnderecoResponseDto> criarEndereco(
      @Valid @RequestBody EnderecoRequestDto enderecoRequestDto);

  @Operation(description = "Endpoint utilizado para trocar o endereço ativo da conta.")
  @PatchMapping("/trocarendereco/{endereco}")
  public ResponseEntity<EnderecoResponseDto> trocarEndereco(
      @PathVariable("endereco") Integer endereco);

  @Operation(description = "Endpoint utilizado para trocar a senha.")
  @PatchMapping("/trocarsenha")
  public ResponseEntity<UsuarioResponseDto> trocarSenha(
      @Valid @RequestBody TrocarSenhaRequestDto trocarSenhaRequestDto);
}
