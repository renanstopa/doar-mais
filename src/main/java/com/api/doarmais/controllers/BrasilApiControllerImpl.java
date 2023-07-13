package com.api.doarmais.controllers;

import com.api.doarmais.clients.BrasilApiClient;
import com.api.doarmais.controllers.interfaces.BrasilApiController;
import com.api.doarmais.dtos.response.CepResponseDto;
import com.api.doarmais.dtos.response.CnpjResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrasilApiControllerImpl implements BrasilApiController {

  @Autowired private BrasilApiClient brasilApiClient;

  public ResponseEntity<CepResponseDto> infoCep(String cep) {
    return ResponseEntity.ok(brasilApiClient.infoCep(cep));
  }

  public ResponseEntity<CnpjResponseDto> infoCnpj(String cnpj) {
    return ResponseEntity.ok(brasilApiClient.infoCnpj(cnpj));
  }
}
