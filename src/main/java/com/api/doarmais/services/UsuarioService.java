package com.api.doarmais.services;

import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioModel criarUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
