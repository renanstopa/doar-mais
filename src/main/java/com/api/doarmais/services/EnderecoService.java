package com.api.doarmais.services;

import com.api.doarmais.dtos.CriarUsuarioDto;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void armazenarEndereco(UsuarioModel usuarioModel, EnderecoModel enderecoModel, CriarUsuarioDto criarUsuarioDto) {
        enderecoModel.setUsuarioModel(usuarioModel);
        enderecoModel.setTxCep(criarUsuarioDto.getTxCep());
        enderecoModel.setTxUf(criarUsuarioDto.getTxUf());
        enderecoModel.setTxBairro(criarUsuarioDto.getTxBairro());
        enderecoModel.setTxCidade(criarUsuarioDto.getTxCidade());
        enderecoModel.setTxLogradouro(criarUsuarioDto.getTxLogradouro());
        enderecoModel.setCdNumero(criarUsuarioDto.getCdNumero());
        enderecoModel.setTxComplemento(criarUsuarioDto.getTxComplemento());
        enderecoModel.setCkAtivo(1);
    }

    public EnderecoModel gravar(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public boolean verificarEnderecoExistente(String cep, Integer numero, Integer usuario) {
        return enderecoRepository.existsByTxCepAndCdNumeroAndUsuarioModelCdUsuario(cep, numero, usuario);
    }

    public EnderecoModel buscarEnderecoAtivo(Integer usuario) {
        return enderecoRepository.findByUsuarioModelCdUsuarioAndCkAtivo(usuario, 1);
    }

    public Optional<EnderecoModel> buscarNonoEndereco(Integer endereco) {
        return enderecoRepository.findById(endereco);
    }
}
