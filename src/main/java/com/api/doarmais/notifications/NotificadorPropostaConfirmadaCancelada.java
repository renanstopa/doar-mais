package com.api.doarmais.notifications;

import com.api.doarmais.events.PropostaCanceladaAnuncioEvent;
import com.api.doarmais.events.PropostaConfirmadaCanceladaEvent;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ItemAnuncioPropostaService;
import com.api.doarmais.services.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableAsync
@Configuration
public class NotificadorPropostaConfirmadaCancelada implements Notificador<PropostaConfirmadaCanceladaEvent> {

  @Autowired private JavaMailSender sender;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @EventListener
  @Async
  public void enviar(PropostaConfirmadaCanceladaEvent propostaConfirmadaCanceladaEvent) throws MessagingException {
    SimpleMailMessage message = new SimpleMailMessage();

    PropostaModel proposta = propostaConfirmadaCanceladaEvent.getPropostaModel();
    UsuarioModel usuario = propostaConfirmadaCanceladaEvent.getUsuarioModel();

    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");
    message.setSubject("Doar+ - Proposta cancelada");

    StringBuilder itens = new StringBuilder();
    List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList = itemAnuncioPropostaService.buscarPorProposta(proposta.getId());
    for (ItemAnuncioPropostaModel itemProposta : itemAnuncioPropostaModelList) {
      itens.append("Item - ").append(itemProposta.getItemAnuncioModel().getNome()).append("\n").append("Quantidade - ").append(itemProposta.getQuantidadeSolicitada()).append("\n\n");
    }

    message.setText("Olá, " + proposta.getUsuarioModel().getNome() + "!\n\n" +
                    "Sua proposta - " + proposta.getAnuncioModel().getTitulo() + "\n\n" + itens +
                    "Agendada para - " + proposta.getDataAgendada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n" +
                    "Foi cancelada pelo seguinte motivo: " + propostaConfirmadaCanceladaEvent.getMotivo());

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}