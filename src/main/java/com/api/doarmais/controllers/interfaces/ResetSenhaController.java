package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.ResetRequestDto;
import com.api.doarmais.dtos.request.TrocarSenhaRequestDto;
import com.api.doarmais.dtos.response.ResetSenhaResponseDto;
import com.api.doarmais.dtos.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Reset de senha",
    description = "Endpoints responsáveis por gerenciar o reset de senha.")
@RequestMapping("/senha")
public interface ResetSenhaController {

  @Operation(
      description = "Endpoint utilizado para enviar a URL de reset de senha no email do usuário.")
  @PostMapping("/reset")
  public ResponseEntity<ResetSenhaResponseDto> enviarEmail(
      @RequestBody @Valid ResetRequestDto resetRequestDto);

  @Operation(description = "Endpoint utilizado para trocar a senha.")
  @PatchMapping("")
  public ResponseEntity<UsuarioResponseDto> trocarSenha(
      @RequestParam("token") String token,
      @RequestBody @Valid TrocarSenhaRequestDto trocarSenhaRequestDto);
}
