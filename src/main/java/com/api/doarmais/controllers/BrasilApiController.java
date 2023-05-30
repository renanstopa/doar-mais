package com.api.doarmais.controllers;

import com.api.doarmais.clients.BrasilApiClient;
import com.api.doarmais.dtos.CepDto;
import com.api.doarmais.dtos.CnpjDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brasilapi")
public class BrasilApiController {

    @Autowired
    private BrasilApiClient brasilApiClient;

    @GetMapping("/infoCep/{cep}")
    public ResponseEntity<CepDto> infoCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(brasilApiClient.infoCep(cep));
    }

    @GetMapping("/infoCnpj/{cnpj}")
    public ResponseEntity<CnpjDto> infoCnpj(@PathVariable("cnpj") String cnpj){
        return ResponseEntity.ok(brasilApiClient.infoCnpj(cnpj));
    }

}
