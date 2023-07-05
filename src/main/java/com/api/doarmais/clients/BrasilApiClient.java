package com.api.doarmais.clients;

import com.api.doarmais.dtos.response.CepResponseDto;
import com.api.doarmais.dtos.response.CnpjResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "brasilapi", url = "https://brasilapi.com.br/api/")
public interface BrasilApiClient {

  @RequestMapping(method = RequestMethod.GET, value = "cep/v2/{cep}")
  CepResponseDto infoCep(@PathVariable("cep") String cep);

  @RequestMapping(method = RequestMethod.GET, value = "cnpj/v1/{cnpj}")
  CnpjResponseDto infoCnpj(@PathVariable("cnpj") String cnpj);
}
