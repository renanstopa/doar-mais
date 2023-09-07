package com.api.doarmais.services;

import com.api.doarmais.dtos.request.CriarUsuarioRequestDto;
import com.api.doarmais.dtos.request.EnderecoRequestDto;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TrocaEnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.EnderecoRepository;
import com.api.doarmais.repositories.TrocaEnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrocaEnderecoService {

  @Autowired private TrocaEnderecoRepository trocaEnderecoRepository;

  public TrocaEnderecoModel criarSolicitacao(EnderecoRequestDto enderecoRequestDto, UsuarioModel usuarioModel) {
    TrocaEnderecoModel trocaEnderecoModel = new TrocaEnderecoModel();
    BeanUtils.copyProperties(enderecoRequestDto, trocaEnderecoModel);
    trocaEnderecoModel.setIdUsuario(usuarioModel.getId());
    trocaEnderecoModel.setIdSituacao(SituacaoModel.SOLICITACAO_TROCA_ENDERECO_POENDENTE);
    return trocaEnderecoRepository.save(trocaEnderecoModel);
  }
}
