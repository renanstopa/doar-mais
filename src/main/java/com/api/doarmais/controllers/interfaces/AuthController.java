package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.CriarUsuarioRequestDto;
import com.api.doarmais.dtos.request.RequestDto;
import com.api.doarmais.dtos.response.ResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Autenticação",
    description = "Endpoints responsáveis por criar conta (usuário ou ONG) e realizar o login.")
@RequestMapping("/auth")
public interface AuthController {

  @Operation(description = "Endpoint utilizado para criar usuário do tipo -> Pessoa.")
  @PostMapping("/registrarusuario")
  public ResponseEntity<UsuarioResponseDto> registrarUsuario(
      @RequestBody @Valid CriarUsuarioRequestDto criarUsuarioRequestDto);

  @Operation(description = "Endpoint utilizado para criar usuário do tipo -> ONG.")
  @PostMapping("/registrarong")
  public ResponseEntity<UsuarioResponseDto> registrarOng(
      @RequestBody @Valid CriarUsuarioRequestDto criarUsuarioRequestDto);

  @Operation(description = "Endpoint utilizado para realizar login no sistema.")
  @PostMapping("/autenticar")
  public ResponseEntity<ResponseDto> authenticate(@RequestBody RequestDto request);
}
