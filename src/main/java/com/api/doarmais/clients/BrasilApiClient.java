package com.api.doarmais.clients;

import com.api.doarmais.dtos.CepDto;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class BrasilApiClient {

    private final String baseUrl = "https://brasilapi.com.br/api/";
    private final Gson gson = new Gson();

    public CepDto buscarCep(String cep) {

        String cepUrl = "cep/v2/";

        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES)).build();

            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(baseUrl + cepUrl + cep)).build();

            HttpResponse<String> httpResponse = httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(httpResponse.body(), CepDto.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
