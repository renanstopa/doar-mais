package com.api.doarmais.notifications;

import com.api.doarmais.events.ContaRecusadaEvent;
import com.api.doarmais.models.tabelas.UsuarioModel;
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
public class NotificadorContaRecusada implements Notificador<ContaRecusadaEvent> {

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(ContaRecusadaEvent contaRecusadaEvent) {
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = contaRecusadaEvent.getUsuarioModel();

    message.setSubject("Doar+ - Conta recusada");
    message.setText(
        "Olá, "
            + usuario.getNome()
            + "\n\nSua conta foi recusada, tente criar outra ou entrar em contato com o site.");
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
