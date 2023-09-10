package com.api.doarmais.services;

import com.api.doarmais.dtos.request.*;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.models.views.BuscaPropostasAgendadasViewModel;
import com.api.doarmais.models.views.BuscaPropostasHistoricoViewModel;
import com.api.doarmais.models.views.BuscaPropostasPendentesViewModel;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

  @Autowired private EntityManager entityManager;

  public List<BuscaAnuncioViewModel> buscarAnuncios(FiltroAnuncioRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaAnuncioViewModel> query = builder.createQuery(BuscaAnuncioViewModel.class);

    filtrarAnuncios(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrarAnuncios(
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

      if (StringUtils.isNotBlank(filtro.getCidade()))
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

    predicate.add(builder.equal(root.get("idUsuarioCriador"), filtro.getIdUsuario()));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataInicioDisponibilidade")));
  }

  public List<BuscaPropostasAgendadasViewModel> buscarAgendados(FiltroAnuncioRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaPropostasAgendadasViewModel> query =
        builder.createQuery(BuscaPropostasAgendadasViewModel.class);

    filtrarAgendados(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrarAgendados(
      FiltroAnuncioRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaPropostasAgendadasViewModel> query) {
    Root<BuscaPropostasAgendadasViewModel> root =
        query.from(BuscaPropostasAgendadasViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    Subquery<ItemAnuncioModel> subQueryItem = query.subquery(ItemAnuncioModel.class);
    Root<ItemAnuncioModel> subRootItem = subQueryItem.from(ItemAnuncioModel.class);

    Subquery<AnuncioModel> subQueryAnuncio = query.subquery(AnuncioModel.class);
    Root<AnuncioModel> subRootAnuncio = subQueryAnuncio.from(AnuncioModel.class);

    if (filtro != null) {
      if (StringUtils.isNotBlank(filtro.getTitulo()))
        predicate.add(builder.like(root.get("titulo"), "%" + filtro.getTitulo() + "%"));

      if (StringUtils.isNotBlank(filtro.getCidade()))
        predicate.add(builder.like(root.get("cidade"), "%" + filtro.getCidade() + "%"));

      if (filtro.getTipoUsuario() != null)
        predicate.add(builder.equal(root.get("idTipoUsuario"), filtro.getTipoUsuario()));

      if (filtro.getTipoAnuncio() != null)
        predicate.add(builder.equal(root.get("idTipoAnuncio"), filtro.getTipoAnuncio()));

      if (filtro.getTipoCategoriaItem() != null) {
        subQueryItem
            .select(subRootItem.get("anuncioModel").get("id"))
            .where(
                builder.equal(
                    subRootItem.get("categoriaItemModel").get("id"),
                    filtro.getTipoCategoriaItem()));
        predicate.add(builder.in(root.get("id")).value(subQueryItem));
      }
    }

    subQueryAnuncio
        .select(subRootAnuncio.get("id"))
        .where(builder.equal(subRootAnuncio.get("usuarioModel").get("id"), filtro.getIdUsuario()));
    predicate.add(
        builder.or(
            builder.equal(root.get("idUsuario"), filtro.getIdUsuario()),
            builder.in(root.get("idAnuncio")).value(subQueryAnuncio)));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataFiltro")));
  }

  public List<BuscaPropostasPendentesViewModel> buscarPendentes(FiltroAnuncioRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaPropostasPendentesViewModel> query =
        builder.createQuery(BuscaPropostasPendentesViewModel.class);

    filtrarPendentes(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrarPendentes(
      FiltroAnuncioRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaPropostasPendentesViewModel> query) {
    Root<BuscaPropostasPendentesViewModel> root =
        query.from(BuscaPropostasPendentesViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    Subquery<ItemAnuncioModel> subQueryItem = query.subquery(ItemAnuncioModel.class);
    Root<ItemAnuncioModel> subRootItem = subQueryItem.from(ItemAnuncioModel.class);

    if (filtro != null) {
      if (StringUtils.isNotBlank(filtro.getTitulo()))
        predicate.add(builder.like(root.get("titulo"), "%" + filtro.getTitulo() + "%"));

      if (StringUtils.isNotBlank(filtro.getCidade()))
        predicate.add(builder.like(root.get("cidade"), "%" + filtro.getCidade() + "%"));

      if (filtro.getTipoUsuario() != null)
        predicate.add(builder.equal(root.get("idTipoUsuario"), filtro.getTipoUsuario()));

      if (filtro.getTipoAnuncio() != null)
        predicate.add(builder.equal(root.get("idTipoAnuncio"), filtro.getTipoAnuncio()));

      if (filtro.getTipoCategoriaItem() != null) {
        subQueryItem
            .select(subRootItem.get("anuncioModel").get("id"))
            .where(
                builder.equal(
                    subRootItem.get("categoriaItemModel").get("id"),
                    filtro.getTipoCategoriaItem()));
        predicate.add(builder.in(root.get("id")).value(subQueryItem));
      }
    }

    predicate.add(builder.equal(root.get("idUsuario"), filtro.getIdUsuario()));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataFiltro")));
  }

  public List<BuscaPropostasHistoricoViewModel> buscarHistorico(FiltroAnuncioRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaPropostasHistoricoViewModel> query =
        builder.createQuery(BuscaPropostasHistoricoViewModel.class);

    filtrarHistorico(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrarHistorico(
      FiltroAnuncioRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaPropostasHistoricoViewModel> query) {
    Root<BuscaPropostasHistoricoViewModel> root =
        query.from(BuscaPropostasHistoricoViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    Subquery<ItemAnuncioModel> subQueryItem = query.subquery(ItemAnuncioModel.class);
    Root<ItemAnuncioModel> subRootItem = subQueryItem.from(ItemAnuncioModel.class);

    Subquery<AnuncioModel> subQueryAnuncio = query.subquery(AnuncioModel.class);
    Root<AnuncioModel> subRootAnuncio = subQueryAnuncio.from(AnuncioModel.class);

    if (filtro != null) {
      if (StringUtils.isNotBlank(filtro.getTitulo()))
        predicate.add(builder.like(root.get("titulo"), "%" + filtro.getTitulo() + "%"));

      if (StringUtils.isNotBlank(filtro.getCidade()))
        predicate.add(builder.like(root.get("cidade"), "%" + filtro.getCidade() + "%"));

      if (filtro.getTipoUsuario() != null)
        predicate.add(builder.equal(root.get("idTipoUsuario"), filtro.getTipoUsuario()));

      if (filtro.getTipoAnuncio() != null)
        predicate.add(builder.equal(root.get("idTipoAnuncio"), filtro.getTipoAnuncio()));

      if (filtro.getTipoCategoriaItem() != null) {
        subQueryItem
            .select(subRootItem.get("anuncioModel").get("id"))
            .where(
                builder.equal(
                    subRootItem.get("categoriaItemModel").get("id"),
                    filtro.getTipoCategoriaItem()));
        predicate.add(builder.in(root.get("id")).value(subQueryItem));
      }

      if (filtro.getSituacao() != null)
        predicate.add(builder.equal(root.get("idSituacao"), filtro.getSituacao()));
    }

    subQueryAnuncio
        .select(subRootAnuncio.get("id"))
        .where(builder.equal(subRootAnuncio.get("usuarioModel").get("id"), filtro.getIdUsuario()));
    predicate.add(
        builder.or(
            builder.equal(root.get("idUsuario"), filtro.getIdUsuario()),
            builder.in(root.get("idAnuncio")).value(subQueryAnuncio)));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataFiltro")));
  }
}
