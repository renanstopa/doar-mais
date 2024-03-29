package com.api.doarmais.notifications;

import com.api.doarmais.events.UsuarioCriadoEvent;
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
public class NotificadorAutenticacaoEmailUsuario implements Notificador<UsuarioCriadoEvent> {

  @Autowired private UsuarioService usuarioService;

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(UsuarioCriadoEvent usuarioCriadoEvent) {
    String url =
        "http://localhost:3000/autenticacaoemail?token="
            + usuarioCriadoEvent.getAutenticacaoEmailModel().getToken();
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario =
        usuarioService.buscarUsuarioPorEmail(
            usuarioCriadoEvent.getAutenticacaoEmailModel().getEmailUsuario());

    message.setSubject("Doar+ - Autenticar conta");
    message.setText(
        "Olá, " + usuario.getNome() + "!\n" + "Para validar seu email entre nesse link " + url);
    message.setTo(usuario.getEmail());
    message.setFrom("doarmaistcc@gmail.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
