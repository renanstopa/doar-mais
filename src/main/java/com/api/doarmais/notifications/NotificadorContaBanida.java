package com.api.doarmais.notifications;

import com.api.doarmais.events.ContaBanidaEvent;
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
public class NotificadorContaBanida implements Notificador<ContaBanidaEvent> {

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(ContaBanidaEvent contaBanidaEvent) {
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = contaBanidaEvent.getUsuarioModel();

    message.setSubject("Doar+ - Conta desbloqueada");
    message.setText("Olá, " + usuario.getNome() + "\n\nSua conta foi banida permanentemente.");
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
