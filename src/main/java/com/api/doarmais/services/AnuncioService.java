package com.api.doarmais.services;

import com.api.doarmais.dtos.request.*;
import com.api.doarmais.events.PossivelPunicaoEvent;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.repositories.AnuncioRepository;
import com.api.doarmais.repositories.ItemAnuncioPropostaRepository;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

  @Autowired private AnuncioRepository anuncioRepository;

  @Autowired private ItemAnuncioRepository itemAnuncioRepository;

  @Autowired private ItemAnuncioPropostaRepository itemAnuncioPropostaRepository;

  @Autowired private EntityManager entityManager;

  @Autowired private ApplicationEventPublisher eventPublisher;

  @Autowired private PunicaoService punicaoService;

  public AnuncioModel gravar(AnuncioModel anuncioModel) {
    return anuncioRepository.save(anuncioModel);
  }

  public void completarInformacoes(AnuncioModel anuncioModel, Integer tipoAnuncio) {
    anuncioModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    anuncioModel.setTipoAnuncioModel(new TipoAnuncioModel(tipoAnuncio));
    anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CRIADO));
    if (anuncioModel.getQuantidadeProposta() == null) anuncioModel.setQuantidadeProposta(0);
  }

  public List<BuscaAnuncioViewModel> buscar(FiltroAnuncioRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaAnuncioViewModel> query = builder.createQuery(BuscaAnuncioViewModel.class);

    filtrar(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrar(
      FiltroAnuncioRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaAnuncioViewModel> query) {
    Root<BuscaAnuncioViewModel> root = query.from(BuscaAnuncioViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    Subquery<ItemAnuncioModel> subquery = query.subquery(ItemAnuncioModel.class);
    Root<ItemAnuncioModel> subRoot = subquery.from(ItemAnuncioModel.class);

    if (filtro != null) {
      if (StringUtils.isNotBlank(filtro.getTitulo()))
        predicate.add(builder.like(root.get("titulo"), "%" + filtro.getTitulo() + "%"));

      predicate.add(builder.like(root.get("cidade"), "%" + filtro.getCidade() + "%"));

      if (filtro.getTipoUsuario() != null)
        predicate.add(builder.equal(root.get("idTipoUsuario"), filtro.getTipoUsuario()));

      if (filtro.getTipoAnuncio() != null)
        predicate.add(builder.equal(root.get("idTipoAnuncio"), filtro.getTipoAnuncio()));

      if (filtro.getTipoCategoriaItem() != null) {
        subquery
            .select(subRoot.get("anuncioModel").get("id"))
            .where(
                builder.equal(
                    subRoot.get("categoriaItemModel").get("id"), filtro.getTipoCategoriaItem()));
        predicate.add(builder.in(root.get("id")).value(subquery));
      }
    }

    predicate.add(builder.notEqual(root.get("idUsuarioCriador"), filtro.getIdUsuario()));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataInicioDisponibilidade")));
  }

  public boolean verificarDataAgendada(PropostaRequestDto propostaRequestDto) {
    AnuncioModel anuncioModel = anuncioRepository.findById(propostaRequestDto.getIdAnuncio()).get();
    LocalDateTime data = propostaRequestDto.getDataAgendada();

    return (data.isAfter(anuncioModel.getDataInicioDisponibilidade())
        && data.isBefore(anuncioModel.getDataFimDisponibilidade()));
  }

  public boolean verificarQuantidadeItem(PropostaRequestDto propostaRequestDto) {
    for (ItemPropostaRequestDto itemProposta : propostaRequestDto.getItemPropostaRequestDtoList()) {
      ItemAnuncioModel itemAnuncioModel =
          itemAnuncioRepository.findById(itemProposta.getIdItem()).get();
      if (itemProposta.getQuantidade() > itemAnuncioModel.getQuantidade()) return false;
    }

    return true;
  }

  public AnuncioModel buscarPorId(Integer idAnuncio) {
    return anuncioRepository.findById(idAnuncio).get();
  }

  public void atualizarSituacao(AnuncioModel anuncioModel) {
    List<ItemAnuncioModel> itemAnuncioModelList =
        itemAnuncioRepository.findByAnuncioModelId(anuncioModel.getId());

    for (ItemAnuncioModel item : itemAnuncioModelList) {
      if (!item.getQuantidade().equals(0)) return;
    }

    anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_ITENS_ESGOTADOS));
    anuncioRepository.save(anuncioModel);
  }

  public void voltarQuantidadeOriginalItem(
      ItemAnuncioModel itemAnuncioModel,
      List<PropostaModel> propostasCanceladas,
      EditarItemAnuncioRequestDto itemDto) {
    for (PropostaModel proposta : propostasCanceladas) {
      List<ItemAnuncioPropostaModel> itemAnuncioPropostaModelList =
          itemAnuncioPropostaRepository.findByPropostaModelId(proposta.getId());

      for (ItemAnuncioPropostaModel itemProposta : itemAnuncioPropostaModelList) {
        if (itemProposta.getItemAnuncioModel().equals(itemAnuncioModel)) {
          itemAnuncioModel.setQuantidade(
              itemAnuncioModel.getQuantidade() + itemProposta.getQuantidadeSolicitada());
          itemDto.setQuantidade(itemAnuncioModel.getQuantidade());
          itemAnuncioRepository.save(itemAnuncioModel);
        }
      }

      AnuncioModel anuncioModel = proposta.getAnuncioModel();
      if (!anuncioModel.getSituacaoModel().getId().equals(22)) {
        anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CRIADO));
        anuncioRepository.save(anuncioModel);
      }
    }
  }

  public void cancelarAnuncio(AnuncioModel anuncioModel) {
    anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CANCELADO));
    anuncioRepository.save(anuncioModel);
  }

  public void verificarEnvioPunicao(
      List<PropostaModel> propostasCanceladas, MotivoCancelamentoDto motivo) {
    boolean envioEmail = false;
    for (PropostaModel proposta : propostasCanceladas) {
      if (LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
          .isAfter(proposta.getDataAgendada().minusHours(3))) {
        if (!envioEmail) {
          eventPublisher.publishEvent(new PossivelPunicaoEvent(proposta));
          envioEmail = true;
        }
        punicaoService.gerarVerificacaoPunicao(proposta, motivo);
      }
    }
  }
}
