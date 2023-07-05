package com.api.doarmais.controllers;

import com.api.doarmais.clients.BrasilApiClient;
import com.api.doarmais.dtos.response.CepResponseDto;
import com.api.doarmais.dtos.response.CnpjResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brasilapi")
public class BrasilApiController {

  @Autowired private BrasilApiClient brasilApiClient;

  @GetMapping("/infocep/{cep}")
  public ResponseEntity<CepResponseDto> infoCep(@PathVariable("cep") String cep) {
    return ResponseEntity.ok(brasilApiClient.infoCep(cep));
  }

  @GetMapping("/infocnpj/{cnpj}")
  public ResponseEntity<CnpjResponseDto> infoCnpj(@PathVariable("cnpj") String cnpj) {
    return ResponseEntity.ok(brasilApiClient.infoCnpj(cnpj));
  }
}
