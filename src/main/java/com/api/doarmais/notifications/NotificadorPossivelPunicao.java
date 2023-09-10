package com.api.doarmais.notifications;

import com.api.doarmais.events.PossivelPunicaoEvent;
import com.api.doarmais.models.tabelas.PropostaModel;
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
public class NotificadorPossivelPunicao implements Notificador<PossivelPunicaoEvent> {

  @Autowired private UsuarioService usuarioService;

  @Autowired private JavaMailSender sender;

  @EventListener
  @Async
  public void enviar(PossivelPunicaoEvent possivelPunicaoEvent) {
    PropostaModel proposta = possivelPunicaoEvent.getPropostaModel();
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = proposta.getAnuncioModel().getUsuarioModel();

    message.setSubject("Doar+ - Possível punição");
    message.setText(
        "Olá, "
            + usuario.getNome()
            + "!\n\n"
            + "Uma punição pode ser aplicada na sua conta por conta de um cancelamento de proposta!\n"
            + "O administrador irá verificar a data/horário do cancelamento com a data agendada para a proposta.");
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
