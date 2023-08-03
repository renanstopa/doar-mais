package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.PropostaRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.ConsultaAnuncioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Doação", description = "Endpoints responsáveis por gerenciar as ações de doações.")
@RequestMapping("/doacao")
public interface DoacaoController {

  @Operation(description = "Endpoint utilizado para criar um anúncio.")
  @PostMapping("")
  public ResponseEntity<AnuncioResponseDto> criarAnuncio(
          @Valid @RequestBody AnuncioRequestDto anuncioRequestDto);

  @Operation(description = "Endpoint utilizado para buscar os anúncio através de filtros")
  @GetMapping("")
  public ResponseEntity<List<BuscaAnuncioViewModel>> buscar(
      @RequestParam(value = "titulo", required = false) String titulo,
      @RequestParam(value = "cidade", required = false) String cidade,
      @RequestParam(value = "tipoUsuario", required = false) Integer tipoUsuario,
      @RequestParam(value = "tipoCategoriaItem", required = false) Integer tipoCategoriaItem);

  @Operation(description = "Endpoint utilizado para consultar os dados do anúncio excolhido")
  @GetMapping("/{id}")
  public ResponseEntity<ConsultaAnuncioViewModel> consultar(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para criar uma proposta referente a um anúncio")
  @PostMapping("/proposta")
  public ResponseEntity<PropostaResponseDto>criarProposta(@Valid @RequestBody PropostaRequestDto propostaRequestDto);

}
