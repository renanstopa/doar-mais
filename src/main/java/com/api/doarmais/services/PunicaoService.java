package com.api.doarmais.services;

import com.api.doarmais.dtos.request.MotivoCancelamentoDto;
import com.api.doarmais.models.tabelas.PropostaModel;
import com.api.doarmais.models.tabelas.PunicaoModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.PunicaoRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PunicaoService {

  @Autowired private PunicaoRepository punicaoRepository;

  public void gerarVerificacaoPunicao(PropostaModel proposta, MotivoCancelamentoDto motivo) {
    UsuarioModel usuarioModel =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    PunicaoModel punicaoModel = new PunicaoModel();

    punicaoModel.setIdUsuario(usuarioModel.getId());
    punicaoModel.setDataAgendada(proposta.getDataAgendada());
    punicaoModel.setDataCancelamento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    punicaoModel.setMotivo(motivo.getMotivo());
    punicaoModel.setIdSituacao(SituacaoModel.PUNICAO_NAO_VERIFICADA);
    punicaoRepository.save(punicaoModel);
  }

  public void gerarVerificacaoPunicao(PropostaModel proposta, String motivo) {
    UsuarioModel usuarioModel =
            (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    PunicaoModel punicaoModel = new PunicaoModel();

    punicaoModel.setIdUsuario(usuarioModel.getId());
    punicaoModel.setDataAgendada(proposta.getDataAgendada());
    punicaoModel.setDataCancelamento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    punicaoModel.setMotivo(motivo);
    punicaoModel.setIdSituacao(SituacaoModel.PUNICAO_NAO_VERIFICADA);
    punicaoRepository.save(punicaoModel);
  }

    public PunicaoModel consultar(Integer id) {
      return punicaoRepository.findById(id).get();
    }

  public void gravar(PunicaoModel punicaoModel) {
    punicaoRepository.save(punicaoModel);
  }
}
