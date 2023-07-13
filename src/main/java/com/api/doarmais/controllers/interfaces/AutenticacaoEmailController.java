package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Autenticar email",
    description = "Endpoints responsáveis por validar a conta do usuário.")
@RequestMapping("/autenticacaoemail")
public interface AutenticacaoEmailController {

  @Operation(
      description =
          "Endpoint utilizado para validar a conta do usuário e ser passada para o adm aprovar.")
  @PatchMapping("/validar/{token}")
  public ResponseEntity<UsuarioResponseDto> autenticarEmail(@PathVariable("token") String token);
}
