package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.FiltroAnuncioRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Anúncio", description = "Endpoints responsáveis por gerenciar o CRUD dos anúncios.")
@RequestMapping("/anuncio")
public interface AnuncioController {

  @Operation(description = "Endpoint utilizado para buscar os anúncio através de filtros")
  @GetMapping("")
  public ResponseEntity<List<BuscaAnuncioViewModel>> buscar(@RequestParam("titulo") String titulo,
                                                            @RequestParam("cidade") String cidade,
                                                            @RequestParam("tipoUsuario") Integer tipoUsuario,
                                                            @RequestParam("tipoAnuncio") Integer tipoAnuncio,
                                                            @RequestParam("tipoCategoriaItem") Integer tipoCategoriaItem);

  @Operation(description = "Endpoint utilizado para criar um anúncio do tipo -> Doação.")
  @PostMapping("/criardoacao")
  public ResponseEntity<AnuncioResponseDto> criarDoacao(
      @Valid @RequestBody AnuncioRequestDto anuncioRequestDto);

  @Operation(description = "Endpoint utilizado para criar um anúncio do tipo -> Pedido.")
  @PostMapping("/criarpedido")
  public ResponseEntity<AnuncioModel> criarPedido(
      @Valid @RequestBody AnuncioRequestDto anuncioRequestDto);
}
