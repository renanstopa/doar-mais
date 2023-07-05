package com.api.doarmais.services;

import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.repositories.PerfilUsuarioViewRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilUsuarioViewService {

  @Autowired private PerfilUsuarioViewRepository perfilUsuarioViewRepository;

  public Optional<PerfilUsuarioViewModel> consultarPerfil(Integer usuario) {
    return perfilUsuarioViewRepository.findById(usuario);
  }
}
