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
    name = "Home",
    description =
        "Endpoints responsáveis pela funcionalidades da página home.")
@RequestMapping("/home")
public interface HomeController {

  @Operation(description = "Endpoint utilizado para gerar uma denúncia referente a bug/melhoria.")
  @PostMapping("/denuncia")
  public ResponseEntity<DenunciaResponseDto> criarDenuncia(
      @Valid @RequestBody DenunciaRequestDto denunciaRequestDto);
}
