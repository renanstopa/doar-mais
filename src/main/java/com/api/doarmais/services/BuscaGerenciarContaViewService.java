package com.api.doarmais.services;

import com.api.doarmais.dtos.request.FiltroGerenciarContaRequestDto;
import com.api.doarmais.models.views.BuscaGerenciarContasViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaGerenciarContaViewService {

  @Autowired private EntityManager entityManager;

  public List<BuscaGerenciarContasViewModel> buscar(FiltroGerenciarContaRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaGerenciarContasViewModel> query =
        builder.createQuery(BuscaGerenciarContasViewModel.class);

    filtrar(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrar(
      FiltroGerenciarContaRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaGerenciarContasViewModel> query) {
    Root<BuscaGerenciarContasViewModel> root = query.from(BuscaGerenciarContasViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    if (filtro != null) {
      if (filtro.getTipoUsuario() != null)
        predicate.add(builder.equal(root.get("idTipoUsuario"), filtro.getTipoUsuario()));
    }

    predicate.add(builder.equal(root.get("idSituacao"), 12));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
  }
}
