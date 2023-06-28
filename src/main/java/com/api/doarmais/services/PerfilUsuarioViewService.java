package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TipoUsuarioModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.repositories.PerfilUsuarioViewRepository;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilUsuarioViewService {

    @Autowired
    private PerfilUsuarioViewRepository perfilUsuarioViewRepository;

    public Optional<PerfilUsuarioViewModel> consultarPerfil(Integer usuario) {
        return perfilUsuarioViewRepository.findById(usuario);
    }
}
