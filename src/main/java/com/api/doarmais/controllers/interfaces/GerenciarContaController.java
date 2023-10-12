package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.models.views.BuscaGerenciarContasViewModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Gerenciar contas",
    description = "Endpoints responsáveis para o adm gerenciar as contas de novos usuários")
@RequestMapping("/gerenciarcontas")
public interface GerenciarContaController {

  @Operation(description = "Endpoint utilizado para buscar as novas contas de usuários")
  @GetMapping("")
  public ResponseEntity<List<BuscaGerenciarContasViewModel>> buscar(
      @RequestParam(value = "tipoUsuario", required = false) Integer tipoUsuario);

  @Operation(description = "Endpoint utilizado para consultar uma nova conta de usuário")
  @GetMapping("/{id}")
  public ResponseEntity<PerfilUsuarioViewModel> consultar(@PathVariable("id") Integer id);

  @Operation(
      description = "Endpoint utilizado para fazer o download do documento enviado pelo usuário")
  @GetMapping("/{id}/download")
  public @ResponseBody HttpEntity<InputStreamResource> download(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para aceitar conta do usuário")
  @PatchMapping("/{id}/aceitarconta")
  public ResponseEntity<PerfilUsuarioViewModel> aceitarConta(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para recusar conta do usuário")
  @PatchMapping("/{id}/recusarconta")
  public ResponseEntity<PerfilUsuarioViewModel> recusarConta(@PathVariable("id") Integer id);
}
