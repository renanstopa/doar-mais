package com.api.doarmais.services;

import com.api.doarmais.dtos.CepDto;
import com.api.doarmais.dtos.CriarUsuarioDto;
import com.api.doarmais.models.EnderecoModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.EnderecoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void armazenarEndereco(UsuarioModel usuarioModel, EnderecoModel enderecoModel, CepDto retorno, CriarUsuarioDto criarUsuarioDto) {
        enderecoModel.setUsuarioModel(usuarioModel);
        enderecoModel.setTxCep(retorno.getCep());
        enderecoModel.setTxUf(retorno.getState());
        enderecoModel.setTxBairro(retorno.getNeighborhood());
        enderecoModel.setTxCidade(retorno.getCity());
        enderecoModel.setTxLogradouro(retorno.getStreet());
        enderecoModel.setCdNumero(criarUsuarioDto.getCdNumero());
        enderecoModel.setTxComplemento(criarUsuarioDto.getTxComplemento());
    }

    public void criarEndereco(EnderecoModel enderecoModel) {
        enderecoRepository.save(enderecoModel);
    }
}
