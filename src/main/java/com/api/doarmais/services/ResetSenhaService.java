package com.api.doarmais.services;

import com.api.doarmais.models.ResetSenhaModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
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
public class ResetSenhaService {

    @Autowired
    private ResetSenhaRepository resetSenhaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender sender;

    public boolean verificarPedidoPorEmail(String email) {
        return resetSenhaRepository.existsByTxEmailUsuarioAndCdSituacao(email, 0);
    }

    public ResetSenhaModel buscarPedido(String email) {
        return resetSenhaRepository.buscarUltimoPedidoQuery(email);
    }

    public ResetSenhaModel gravar(ResetSenhaModel resetSenhaModel) {
        return resetSenhaRepository.save(resetSenhaModel);
    }

    public Optional<ResetSenhaModel> buscarPedidoCriado(ResetSenhaModel pedidoGerado) {
        return resetSenhaRepository.findById(pedidoGerado.getCdResetSenha());
    }

    public ResetSenhaModel gerarPedido(String email) {
        String token = UUID.randomUUID().toString();
        LocalDateTime validade = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(1);

        ResetSenhaModel resetSenhaModel = new ResetSenhaModel();
        resetSenhaModel.setCdSituacao(SituacaoModel.TOKEN_NAO_UTILIZADO);
        resetSenhaModel.setTxEmailUsuario(email);
        resetSenhaModel.setTxToken(token);
        resetSenhaModel.setDtValidadeToken(validade);
        return resetSenhaModel;
    }

    public void enviarEmail(ResetSenhaModel pedidoGerado) {
        String url = "https://localhost:8080/resetsenha/trocarsenha/" + pedidoGerado.getTxToken();
        SimpleMailMessage message = new SimpleMailMessage();
        UsuarioModel usuario = usuarioService.buscarUsuarioPorEmail(pedidoGerado.getTxEmailUsuario());

        message.setSubject("Doar+ - Alteração de senha");
        message.setText("Olá, " + usuario.getTxUsuario() + "!\n" +
                "Para realizar a alteração de sua senha entre nesse link " + url);
        message.setTo(usuario.getTxEmail());
        message.setFrom("doar.mais@outlook.com");

        try{
            sender.send(message);
        } catch(Exception e) {
            throw new MailSendException(e.getMessage());
        }
    }

    public ResetSenhaModel buscarPedidoPorToken(String token) {
        return resetSenhaRepository.findByTxToken(token);
    }

    public boolean verificarPedidoPorToken(String token) {
        return resetSenhaRepository.existsByTxToken(token);
    }

    public boolean expirou(ResetSenhaModel resetSenhaModel) {
        return resetSenhaModel.getDtValidadeToken().isBefore(LocalDateTime.now(ZoneId.of(TimeZone.getDefault().getID())));
    }
}
