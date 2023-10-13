package com.api.doarmais.notifications;

import com.api.doarmais.events.ContaAceitaEvent;
import com.api.doarmais.events.ContaSuspensaEvent;
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
public class NotificadorContaSuspensa implements Notificador<ContaSuspensaEvent> {

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(ContaSuspensaEvent contaSuspensaEvent) {
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = contaSuspensaEvent.getUsuarioModel();

    message.setSubject("Doar+ - Conta suspensa");
    message.setText("Olá, " + usuario.getNome() + "\n\nSua conta foi bloqueada por tempo indeterminado por causa de recentes cancelamentos de propostas perto da data agendada.");
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
