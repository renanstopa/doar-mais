package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.TipoUsuarioModel;
import com.api.doarmais.repositories.TipoUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService {

  @Autowired private TipoUsuarioRepository tipoUsuarioRepository;

  public void saveAllTipoUsuarios(List<TipoUsuarioModel> tipoUsuarioModels) {
    tipoUsuarioRepository.saveAll(tipoUsuarioModels);
  }

  public Optional<TipoUsuarioModel> buscarTipoUsuario(Integer cdTipoUsuario) {
    return tipoUsuarioRepository.findById(cdTipoUsuario);
  }

  public boolean verificarExistencia() {
    return tipoUsuarioRepository.existsById(1);
  }
}
