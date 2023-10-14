package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.models.views.BuscaGerenciarContaBloqueadaViewModel;
import com.api.doarmais.models.views.ConsultaGerenciarTrocaEnderecoViewModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Gerenciar contas bloqueadas",
    description = "Endpoints responsáveis para o adm gerenciar contas bloqueadas de usuários")
@RequestMapping("/gerenciarbloqueadas")
public interface GerenciarContaBloqueadaController {

  @Operation(description = "Endpoint utilizado para buscar contas bloqueadas")
  @GetMapping("")
  public ResponseEntity<List<BuscaGerenciarContaBloqueadaViewModel>> buscar();

  @Operation(description = "Endpoint responsável por consultar uma conta bloqueada")
  @GetMapping("/{id}")
  public ResponseEntity<PerfilUsuarioViewModel> consultar(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint responsável por desbloquear a conta")
  @PatchMapping("/{id}/desbloquear")
  public ResponseEntity<PerfilUsuarioViewModel> desbloquear(@PathVariable("id") Integer id);

  @Operation(description = "Endpoint responsável por banir a conta")
  @PatchMapping("/{id}/banir")
  public ResponseEntity<PerfilUsuarioViewModel> banir(@PathVariable("id") Integer id);

}
