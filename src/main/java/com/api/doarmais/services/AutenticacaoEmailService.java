package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.AutenticacaoEmailModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.repositories.AutenticacaoEmailRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoEmailService {

  @Autowired private AutenticacaoEmailRepository autenticacaoEmailRepository;

  @Autowired private UsuarioService usuarioService;

  @Autowired private JavaMailSender sender;

  public AutenticacaoEmailModel gerarAutenticacaoEmail(String email) {
    String token = UUID.randomUUID().toString();

    AutenticacaoEmailModel autenticacaoEmailModel = new AutenticacaoEmailModel();
    autenticacaoEmailModel.setId(SituacaoModel.TOKEN_NAO_UTILIZADO);
    autenticacaoEmailModel.setEmailUsuario(email);
    autenticacaoEmailModel.setToken(token);
    return autenticacaoEmailModel;
  }

  public void gravar(AutenticacaoEmailModel autenticacaoGerada) {
    autenticacaoEmailRepository.save(autenticacaoGerada);
  }

  public boolean verificarPedidoPorToken(String token) {
    return autenticacaoEmailRepository.existsByToken(token);
  }

  public AutenticacaoEmailModel buscarPorToken(String token) {
    return autenticacaoEmailRepository.findByToken(token).get(0);
  }
}
