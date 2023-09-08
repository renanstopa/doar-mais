package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.request.EditarAnuncioRequestDto;
import com.api.doarmais.dtos.request.MotivoCancelamentoDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.PropostaResponseDto;
import com.api.doarmais.models.views.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Atividade", description = "Endpoints responsáveis por gerenciar as ações das minhas atividades.")
@RequestMapping("/atividades")
public interface AtividadeController {

    //ENDPOINTS UTILIZADOS NA ABA DE ANÚNCIOS

    @Operation(description = "Endpoint utilizado para buscar os anúncio através de filtros")
    @GetMapping("/anuncios")
    public ResponseEntity<List<BuscaAnuncioViewModel>> buscarAnuncios(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "tipoUsuario", required = false) Integer tipoUsuario,
            @RequestParam(value = "tipoAnuncio", required = false) Integer tipoAnuncio,
            @RequestParam(value = "tipoCategoriaItem", required = false) Integer tipoCategoriaItem);

    @Operation(description = "Endpoint utilizado para consultar um anúncio")
    @GetMapping("/anuncios/{id}")
    public ResponseEntity<ConsultaAnuncioViewModel> consultarAnuncio(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint utilizado para editar um anúncio.")
    @PatchMapping("/anuncios/{id}")
    public ResponseEntity<AnuncioResponseDto> editarAnuncio(@PathVariable("id") Integer id,
                                                            @Valid @RequestBody EditarAnuncioRequestDto editarAnuncioRequestDto);

    @Operation(description = "Endpoint utilizado para cancelar um anúncio.")
    @PatchMapping("/anuncios/{id}/cancelar")
    public ResponseEntity<AnuncioResponseDto> cancelarAnuncio(@PathVariable("id") Integer id, @Valid @RequestBody MotivoCancelamentoDto motivoCancelamentoDto);

    //ENDPOINTS UTILIZADOS NA ABA DE CONFIRMAÇÕES

    @Operation(description = "Endpoint utilizado para buscar propostas pendentes através de filtros")
    @GetMapping("/pendentes")
    public ResponseEntity<List<BuscaPropostasPendentesViewModel>> buscarPendentes(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "tipoAnuncio", required = false) Integer tipoAnuncio,
            @RequestParam(value = "tipoCategoriaItem", required = false) Integer tipoCategoriaItem);

    @Operation(description = "Endpoint utilizado para consultar uma prosposta pendentes")
    @GetMapping("/pendentes/{id}")
    public ResponseEntity<ConsultaPropostaViewModel> consultarPendente(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint utilizado para confirmar uma proposta")
    @PatchMapping("/pendentes/{id}/confirmar")
    public ResponseEntity<ConsultaPropostaViewModel> confirmarPropostaPendente(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint utilizado para recusar uma proposta")
    @PatchMapping("/pendentes/{id}/recusar")
    public ResponseEntity<ConsultaPropostaViewModel> recusarPropostaPendente(@PathVariable("id") Integer id);

    //ENDPOINTS UTILIZADOS NA ABA DE AGENDADOS

    @Operation(description = "Endpoint utilizado para buscar as propostas confirmadas através de filtros")
    @GetMapping("/agendados")
    public ResponseEntity<List<BuscaPropostasAgendadasViewModel>> buscarAgendados(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "tipoUsuario", required = false) Integer tipoUsuario,
            @RequestParam(value = "tipoAnuncio", required = false) Integer tipoAnuncio,
            @RequestParam(value = "tipoCategoriaItem", required = false) Integer tipoCategoriaItem);

    @Operation(description = "Endpoint utilizado para consultar uma prosposta confirmada")
    @GetMapping("/agendados/{id}")
    public ResponseEntity<ConsultaPropostaViewModel> consultarAgendado(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint utilizado para cancelar uma proposta confirmada")
    @PatchMapping("/agendados/{id}/cancelar")
    public ResponseEntity<PropostaResponseDto> cancelarAgendado(@PathVariable("id") Integer id, @Valid @RequestBody MotivoCancelamentoDto motivoCancelamentoDto);

    //ENDPOINTS UTILIZADOS NA ABA DE HISTÓRICO
}
