package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.DenunciaRequestDto;
import com.api.doarmais.dtos.response.DenunciaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Denúncia",
    description =
        "Endpoints responsáveis por criar uma denúncia/bug/melhoria que será enviada para o adm.")
@RequestMapping("/denuncia")
public interface DenunciaController {

  @Operation(description = "Endpoint utilizado para gerar uma denúncia/bug/melhoria.")
  @PostMapping("")
  public ResponseEntity<DenunciaResponseDto> criarDenuncia(
      @Valid @RequestBody DenunciaRequestDto denunciaRequestDto);
}
