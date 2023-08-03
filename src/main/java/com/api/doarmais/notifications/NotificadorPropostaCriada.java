package com.api.doarmais.notifications;

import com.api.doarmais.events.PropostaCriadaEvent;
import com.api.doarmais.events.ResetCriadoEvent;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ItemAnuncioPropostaService;
import com.api.doarmais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

@EnableAsync
@Configuration
public class NotificadorPropostaCriada implements Notificador<PropostaCriadaEvent> {

  @Autowired private JavaMailSender sender;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @EventListener
  @Async
  public void enviar(PropostaCriadaEvent propostaCriadaEvent) {
    PropostaModel proposta = propostaCriadaEvent.getPropostaModel();
    SimpleMailMessage message = new SimpleMailMessage();
    UsuarioModel usuario = usuarioService.buscarUsuarioPorEmail(proposta.getAnuncioModel().getUsuarioModel().getEmail());
    String itensPedidos = "";
    List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList = itemAnuncioPropostaService.buscarPorProposta(proposta.getId());

    for (ItemAnuncioPropostaModel item : itemAnuncioPropostaModelList) {
      itensPedidos += "Item - " + item.getItemAnuncioModel().getNome() + "\n" +
                      "Quantidade: - " + item.getQuantidadeSolicitada() + "\n\n";
    }

    message.setSubject("Doar+ - Proposta recebida");
    message.setText("Olá, " + usuario.getNome() + "!\n\n" +
                    "Viemos te avisar que " + proposta.getUsuarioModel().getNome() + " se interessou em seu anúncio\n" +
                    "Abaixo estarão os item desejados:\n\n" +
                    "Data agendada - " + proposta.getDataAgendada() + "\n" +
                    "Anúncio - " + proposta.getAnuncioModel().getTitulo() + "\n\n" +
                    itensPedidos);
    message.setTo(usuario.getEmail());
    message.setFrom("doar.mais@outlook.com");

    try {
      sender.send(message);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
