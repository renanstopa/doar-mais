package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.models.views.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Gerenciar denúncias",
    description = "Endpoints responsáveis para o adm gerenciar as denúncias criadas por usuários")
@RequestMapping("/gerenciardenuncias")
public interface GerenciarDenunciaController {

  @Operation(description = "Endpoint utilizado para buscar as denúncias")
  @GetMapping("")
  public ResponseEntity<List<BuscaGerenciarDenunciaViewModel>> buscar(
      @RequestParam(value = "tipoDenuncia", required = false) Integer tipoDenuncia);

  @Operation(description = "Endpoint utilizado para consultar uma denúncia de usuário")
  @GetMapping("/usuarios/{id}")
  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> consultarDenunciaUsuario(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para consultar uma denúncia de anúncio")
  @GetMapping("/anuncios/{id}")
  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> consultarDenunciaAnuncio(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para consultar uma melhoria ou bug")
  @GetMapping("/melhoriabugs/{id}")
  public ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel> consultarMelhoriaBug(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para punir o usuário")
  @PatchMapping("usuarios/{id}/punir")
  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> punirUsuario(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para verificar denúncia de usuário")
  @PatchMapping("usuarios/{id}/verificar")
  public ResponseEntity<ConsultaGerenciarUsuarioDenunciadoViewModel> verificarDenunciaUsuario(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para suspender o anúncio")
  @PatchMapping("anuncios/{id}/suspender")
  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> suspenderAnuncio(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para verificar denúncia de anúncio")
  @PatchMapping("anuncios/{id}/verificar")
  public ResponseEntity<ConsultaGerenciarAnuncioDenunciadoViewModel> verificarDenunciaAnuncio(
      @PathVariable("id") Integer id);

  @Operation(description = "Endpoint utilizado para suspender o anúncio")
  @PatchMapping("melhoriabugs/{id}/verificar")
  public ResponseEntity<ConsultaGerenciarMelhoriaBugViewModel> verificarMelhoriaBug(
      @PathVariable("id") Integer id);
}
