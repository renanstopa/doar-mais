package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.ResetSenhaModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.repositories.ResetSenhaRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetSenhaService {

  @Autowired private ResetSenhaRepository resetSenhaRepository;

  @Autowired private UsuarioService usuarioService;

  @Autowired private JavaMailSender sender;

  public boolean verificarPedidoPorEmail(String email) {
    return resetSenhaRepository.existsByEmailUsuarioAndIdSituacao(email, 0);
  }

  public ResetSenhaModel buscarPedido(String email) {
    return resetSenhaRepository.buscarUltimoPedidoQuery(email);
  }

  public ResetSenhaModel gravar(ResetSenhaModel resetSenhaModel) {
    return resetSenhaRepository.save(resetSenhaModel);
  }

  public Optional<ResetSenhaModel> buscarPedidoCriado(ResetSenhaModel pedidoGerado) {
    return resetSenhaRepository.findById(pedidoGerado.getId());
  }

  public ResetSenhaModel gerarPedido(String email) {
    String token = UUID.randomUUID().toString();
    LocalDateTime validade = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1);

    ResetSenhaModel resetSenhaModel = new ResetSenhaModel();
    resetSenhaModel.setId(SituacaoModel.TOKEN_NAO_UTILIZADO);
    resetSenhaModel.setEmailUsuario(email);
    resetSenhaModel.setToken(token);
    resetSenhaModel.setDataValidade(validade);
    return resetSenhaModel;
  }

  public ResetSenhaModel buscarPedidoPorToken(String token) {
    return resetSenhaRepository.findByToken(token);
  }

  public boolean verificarPedidoPorToken(String token) {
    return resetSenhaRepository.existsByToken(token);
  }

  public boolean expirou(ResetSenhaModel resetSenhaModel) {
    return resetSenhaModel
        .getDataValidade()
        .isBefore(LocalDateTime.now(ZoneId.of(TimeZone.getDefault().getID())));
  }
}
