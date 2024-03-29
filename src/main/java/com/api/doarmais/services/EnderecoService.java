package com.api.doarmais.services;

import com.api.doarmais.dtos.request.CriarUsuarioRequestDto;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.EnderecoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

  @Autowired private EnderecoRepository enderecoRepository;

  public void armazenarEndereco(
      UsuarioModel usuarioModel,
      EnderecoModel enderecoModel,
      CriarUsuarioRequestDto criarUsuarioRequestDto) {
    enderecoModel.setUsuarioModel(usuarioModel);
    enderecoModel.setCep(criarUsuarioRequestDto.getCep());
    enderecoModel.setUf(criarUsuarioRequestDto.getUf());
    enderecoModel.setBairro(criarUsuarioRequestDto.getBairro());
    enderecoModel.setCidade(criarUsuarioRequestDto.getCidade());
    enderecoModel.setLogradouro(criarUsuarioRequestDto.getLogradouro());
    enderecoModel.setNumero(criarUsuarioRequestDto.getNumero());
    enderecoModel.setComplemento(criarUsuarioRequestDto.getComplemento());
  }

  public EnderecoModel gravar(EnderecoModel enderecoModel) {
    return enderecoRepository.save(enderecoModel);
  }

  public Optional<EnderecoModel> buscarNovoEndereco(Integer endereco) {
    return enderecoRepository.findById(endereco);
  }

  public void deletar(Integer idEndereco) {
    enderecoRepository.deleteById(idEndereco);
  }

  public Integer verificarQtdEndereco(Integer idUsuario) {
    return enderecoRepository.verificarQtdEnderecoQuery(idUsuario);
  }

  public EnderecoModel buscarEndereco(Integer idUsuario) {
    return enderecoRepository.findByUsuarioModelId(idUsuario);
  }
}
