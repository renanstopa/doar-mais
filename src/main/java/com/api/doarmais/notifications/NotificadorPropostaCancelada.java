package com.api.doarmais.notifications;

import com.api.doarmais.events.PropostaCriadaEvent;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ItemAnuncioPropostaService;
import com.api.doarmais.services.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableAsync
@Configuration
public class NotificadorPropostaCancelada implements Notificador<PropostaCriadaEvent> {

  @Autowired private JavaMailSender sender;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @EventListener
  @Async
  public void enviar(PropostaCriadaEvent propostaCriadaEvent) throws MessagingException {
    SimpleMailMessage message = new SimpleMailMessage();

    PropostaModel proposta = propostaCriadaEvent.getPropostaModel();
    UsuarioModel usuario = usuarioService.buscarUsuarioPorEmail(proposta.getAnuncioModel().getUsuarioModel().getEmail());

    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");
    message.setSubject("Doar+ - Proposta cancelada");

    message.setText("Olá, " + proposta.getUsuarioModel().getNome() + "!\n\n" +
                    "Sua proposta " + proposta.getAnuncioModel().getTitulo() +
                    " agendada para " + proposta.getDataAgendada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                    " foi cancelada, pois o usuário que criou precisou editar o anúncio!");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
