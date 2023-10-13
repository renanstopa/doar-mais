package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.models.views.BuscaGerenciarPunicaoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarPunicaoViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Gerenciar possíveis punições",
    description = "Endpoints responsáveis para o adm gerenciar os pedidos de punições gerados pelo sistema")
@RequestMapping("/gerenciarpunicoes")
public interface GerenciarPunicaoController {

  @Operation(description = "Endpoint utilizado para buscar as punições geradas")
  @GetMapping("")
  public ResponseEntity<List<BuscaGerenciarPunicaoViewModel>> buscar();

  @Operation(description = "Endpoint utilizado para consultar uma punição")
  @GetMapping("/{id}")
  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> consultar(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para punir o usuário")
  @PatchMapping("/{id}/punir")
  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> punir(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para não punir o usuário")
  @PatchMapping("/{id}/naopunir")
  public ResponseEntity<ConsultaGerenciarPunicaoViewModel> naopunir(@PathVariable("id") Integer id);
}
