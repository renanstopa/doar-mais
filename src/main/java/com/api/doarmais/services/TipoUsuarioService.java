package com.api.doarmais.services;

import com.api.doarmais.models.TipoUsuarioModel;
import com.api.doarmais.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public void deleteAllTipoUsuarios() {
        tipoUsuarioRepository.deleteAll();
    }

    public void saveAllTipoUsuarios(List<TipoUsuarioModel> tipoUsuarioModels) {
        tipoUsuarioRepository.saveAll(tipoUsuarioModels);
    }

    public TipoUsuarioModel buscarTipoUsuario(Integer cdTipoUsuario) {
        return tipoUsuarioRepository.findByCdTipoUsuario(cdTipoUsuario);
    }

}
