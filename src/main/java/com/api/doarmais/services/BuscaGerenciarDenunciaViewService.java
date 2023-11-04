package com.api.doarmais.services;

import com.api.doarmais.dtos.request.FiltroGerenciarDenunciaRequestDto;
import com.api.doarmais.models.views.BuscaGerenciarDenunciaViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaGerenciarDenunciaViewService {

  @Autowired private EntityManager entityManager;

  public List<BuscaGerenciarDenunciaViewModel> buscar(FiltroGerenciarDenunciaRequestDto filtro) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BuscaGerenciarDenunciaViewModel> query =
        builder.createQuery(BuscaGerenciarDenunciaViewModel.class);

    filtrar(filtro, builder, query);

    return entityManager.createQuery(query).getResultList();
  }

  private void filtrar(
      FiltroGerenciarDenunciaRequestDto filtro,
      CriteriaBuilder builder,
      CriteriaQuery<BuscaGerenciarDenunciaViewModel> query) {
    Root<BuscaGerenciarDenunciaViewModel> root = query.from(BuscaGerenciarDenunciaViewModel.class);
    List<Predicate> predicate = new ArrayList<Predicate>();

    if (filtro != null) {
      if (filtro.getTipoDenuncia() != null)
        predicate.add(builder.equal(root.get("idTipoDenuncia"), filtro.getTipoDenuncia()));
    }

    predicate.add(builder.equal(root.get("idSituacao"), 41));

    query.where(builder.and(predicate.toArray(new Predicate[predicate.size()])));
  }
}
