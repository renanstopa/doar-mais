package com.api.doarmais.services;

import com.api.doarmais.dtos.CriarUsuarioDto;
import com.api.doarmais.models.EnderecoModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    }

    public void criarEndereco(EnderecoModel enderecoModel) {
        enderecoRepository.save(enderecoModel);
    }
}
