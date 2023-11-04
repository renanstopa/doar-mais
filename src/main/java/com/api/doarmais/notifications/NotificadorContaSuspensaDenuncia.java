package com.api.doarmais.notifications;

import com.api.doarmais.events.ContaSuspensaDenunciaEvent;
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
public class NotificadorContaSuspensaDenuncia implements Notificador<ContaSuspensaDenunciaEvent> {

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(ContaSuspensaDenunciaEvent contaSuspensaDenunciaEvent) {
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = contaSuspensaDenunciaEvent.getUsuarioModel();

    message.setSubject("Doar+ - Conta suspensa");
    message.setText(
        "Olá, "
            + usuario.getNome()
            + "\n\nSua conta foi bloqueada pelo motivo apontado pelo usuário: \n\n"
            + contaSuspensaDenunciaEvent.getMotivo());
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
