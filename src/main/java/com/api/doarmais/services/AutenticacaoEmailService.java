package com.api.doarmais.services;

import com.api.doarmais.models.AutenticacaoEmailModel;
import com.api.doarmais.models.ResetSenhaModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.AutenticacaoEmailRepository;
import com.api.doarmais.repositories.ResetSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class AutenticacaoEmailService {

    @Autowired
    private AutenticacaoEmailRepository autenticacaoEmailRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender sender;

    public AutenticacaoEmailModel gerarAutenticacaoEmail(String email) {
        String token = UUID.randomUUID().toString();

        AutenticacaoEmailModel autenticacaoEmailModel = new AutenticacaoEmailModel();
        autenticacaoEmailModel.setCdSituacao(SituacaoModel.TOKEN_NAO_UTILIZADO);
        autenticacaoEmailModel.setTxEmailUsuario(email);
        autenticacaoEmailModel.setTxToken(token);
        return autenticacaoEmailModel;
    }

    public void enviarEmail(AutenticacaoEmailModel autenticacaoGerada) {
        String url = "https://localhost:8080/autenticacaoemail/validar/" + autenticacaoGerada.getTxToken();
        SimpleMailMessage message = new SimpleMailMessage();
        UsuarioModel usuario = usuarioService.buscarUsuarioPorEmail(autenticacaoGerada.getTxEmailUsuario());

        message.setSubject("Doar+ - Autenticar conta");
        message.setText("Olá, " + usuario.getTxUsuario() + "!\n" +
                "Para validar seu email entre nesse link " + url);
        message.setTo(usuario.getTxEmail());
        message.setFrom("doar.mais@outlook.com");

        try{
            sender.send(message);
        } catch(Exception e) {
            throw new MailSendException(e.getMessage());
        }
    }

    public void gravar(AutenticacaoEmailModel autenticacaoGerada) {
        autenticacaoEmailRepository.save(autenticacaoGerada);
    }

    public boolean verificarPedidoPorToken(String token) {
        return autenticacaoEmailRepository.existsByTxToken(token);
    }

    public AutenticacaoEmailModel buscarPorToken(String token) {
        return autenticacaoEmailRepository.findByTxToken(token).get(0);
    }
}
