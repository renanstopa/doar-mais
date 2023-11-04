package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.models.views.BuscaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Gerenciar solicitações de troca de endereço",
    description =
        "Endpoints responsáveis para o adm gerenciar as solicitações de trocas de endereços")
@RequestMapping("/gerenciartrocaenderecos")
public interface GerenciarTrocaEnderecoController {

  @Operation(description = "Endpoint utilizado para buscar as solicitações de troca de endereço")
  @GetMapping("")
  public ResponseEntity<List<BuscaGerenciarTrocaEnderecoViewModel>> buscar();

  @Operation(
      description = "Endpoint responsável por consultar uma solicitação de troca de endereço")
  @GetMapping("/{id}")
  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> consultar(
      @PathVariable("id") Integer id);

  @Operation(
      description = "Endpoint responsável por fazer o download do documento enviado pelo usuário")
  @GetMapping("/{id}/download")
  public @ResponseBody HttpEntity<InputStreamResource> download(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint responsável por aceitar a solicitação de troca de endereço")
  @PatchMapping("/{id}/aceitar")
  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> aceitar(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint responsável por recusar a solicitação de troca de endereço")
  @PatchMapping("/{id}/recusar")
  public ResponseEntity<ConsultaGerenciarTrocaEnderecoViewModel> recusar(
      @PathVariable("id") Integer id);
}
