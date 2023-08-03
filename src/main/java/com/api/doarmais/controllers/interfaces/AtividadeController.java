package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.EditarAnuncioRequestDto;
import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Atividade", description = "Endpoints responsáveis por gerenciar as ações das minhas atividades.")
@RequestMapping("/atividades")
public interface AtividadeController {

  @Operation(description = "Endpoint utilizado para editar um anúncio.")
  @PatchMapping("/{id}")
  public ResponseEntity<AnuncioResponseDto> editarAnuncio( @PathVariable("id") Integer id,
          @Valid @RequestBody EditarAnuncioRequestDto editarAnuncioRequestDto);


}
