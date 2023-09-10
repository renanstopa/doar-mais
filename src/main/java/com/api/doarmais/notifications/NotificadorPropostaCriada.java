package com.api.doarmais.notifications;

import com.api.doarmais.events.PropostaCriadaEvent;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ItemAnuncioPropostaService;
import com.api.doarmais.services.UsuarioService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class NotificadorPropostaCriada implements Notificador<PropostaCriadaEvent> {

  @Autowired private JavaMailSender sender;

  @Autowired private UsuarioService usuarioService;

  @Autowired private ItemAnuncioPropostaService itemAnuncioPropostaService;

  @EventListener
  @Async
  public void enviar(PropostaCriadaEvent propostaCriadaEvent) throws MessagingException {
    MimeMessage mimeMessage = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    PropostaModel proposta = propostaCriadaEvent.getPropostaModel();
    UsuarioModel usuario =
        usuarioService.buscarUsuarioPorEmail(
            proposta.getAnuncioModel().getUsuarioModel().getEmail());

    helper.setTo(usuario.getEmail());
    helper.setFrom("doar.mais@outlook.com");
    helper.setSubject("Doar+ - Proposta recebida");

    String titulo = "Olá, " + usuario.getNome() + "!<br><br>";
    String conteudo =
        "Viemos te avisar que "
            + proposta.getUsuarioModel().getNome()
            + " se interessou em seu anúncio<br>"
            + "Abaixo estarão as informações: <br><br><br>"
            + "Data agendada - "
            + proposta.getDataAgendada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            + "<br>";
    String anuncio = "Anúncio - " + proposta.getAnuncioModel().getTitulo() + "<br><br>";
    String itensPedidos = "";
    List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList =
        itemAnuncioPropostaService.buscarPorProposta(proposta.getId());

    for (ItemAnuncioPropostaModel item : itemAnuncioPropostaModelList) {
      itensPedidos +=
          "Item - "
              + item.getItemAnuncioModel().getNome()
              + "<br>"
              + "Quantidade - "
              + item.getQuantidadeSolicitada()
              + "<br><br>";
    }

    String botoes =
        "<a href=\"https://localhost:8080/doacao/"
            + proposta.getAnuncioModel().getId()
            + "\"><button style=\"padding: 8px; font-weight: bold; background-color: #5865F2; border-radius: 9px; border-style: none; color: white; cursor: pointer;\">VER ANÚNCIO</button></a>\n"
            + "<a href=\"\"><button style=\"padding: 8px; font-weight: bold; background-color: #27AE60; border-radius: 9px; border-style: none; color: white; cursor: pointer;\">ACEITAR</button></a>\n"
            + "<a href=\"\"><button style=\"padding: 8px; font-weight: bold; background-color: #FF8164; border-radius: 9px; border-style: none; color: white; cursor: pointer;\">RECUSAR</button></a>";

    helper.setText(titulo + "" + conteudo + "" + anuncio + "" + itensPedidos + "" + botoes, true);

    try {
      sender.send(mimeMessage);
    } catch (Exception e) {
      throw new MailSendException(e.getMessage());
    }
  }
}
