package com.api.doarmais.controllers.interfaces;

import com.api.doarmais.dtos.response.CepResponseDto;
import com.api.doarmais.dtos.response.CnpjResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(
    name = "Brasil API",
    description = "Endpoints responsáveis por consumir API de consulta de CEP e CNPj.")
@RequestMapping("/brasilapi")
public interface BrasilApiController {

  @Operation(description = "Endpoint utilizado para consultar as informações a partir de um CEP.")
  @GetMapping("/infocep/{cep}")
  public ResponseEntity<CepResponseDto> infoCep(@PathVariable("cep") String cep);

  @Operation(description = "Endpoint utilizado para consultar as informações a partir de um CNPJ.")
  @GetMapping("/infocnpj/{cnpj}")
  public ResponseEntity<CnpjResponseDto> infoCnpj(@PathVariable("cnpj") String cnpj);
}
