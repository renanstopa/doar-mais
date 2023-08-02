package com.api.doarmais.services;

import com.api.doarmais.dtos.request.FiltroAnuncioRequestDto;
import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.models.views.BuscaAnuncioViewModel;
import com.api.doarmais.repositories.DoacaoRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService {

  @Autowired private DoacaoRepository doacaoRepository;

  @Autowired private EntityManager entityManager;

  public AnuncioModel gravar(AnuncioModel anuncioModel) {
    return doacaoRepository.save(anuncioModel);
  }

  public void completarInformacoes(AnuncioModel anuncioModel, Integer tipoAnuncio) {
    anuncioModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    anuncioModel.setTipoAnuncioModel(new TipoAnuncioModel(tipoAnuncio));
    anuncioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.ANUNCIO_CRIADO));
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
    var usuarioLogado =
        (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    predicate.add(builder.notEqual(root.get("idUsuarioCriador"), usuarioLogado.getId()));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
    query.orderBy(builder.asc(root.get("dataInicioDisponibilidade")));
  }
}
