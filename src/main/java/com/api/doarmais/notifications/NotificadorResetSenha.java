package com.api.doarmais.notifications;

import com.api.doarmais.events.ResetCriadoEvent;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class NotificadorResetSenha implements Notificador<ResetCriadoEvent> {

  @Autowired private UsuarioService usuarioService;

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(ResetCriadoEvent resetCriadoEvent) {
    String url =
        "http://localhost:3000/senha/?token=" + resetCriadoEvent.getPedidoGerado().getToken();
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario =
        usuarioService.buscarUsuarioPorEmail(resetCriadoEvent.getPedidoGerado().getEmailUsuario());

    message.setSubject("Doar+ - Alteração de senha");
    message.setText(
        "Olá, "
            + usuario.getNome()
            + "!\n"
            + "Para realizar a alteração de sua senha entre nesse link "
            + url);
    message.setTo(usuario.getEmail());
    message.setFrom("doarmaistcc@gmail.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
