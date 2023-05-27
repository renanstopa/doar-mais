package com.api.doarmais.services;

import com.api.doarmais.models.TipoUsuarioModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    public UsuarioModel criarUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public Optional<UsuarioModel> buscarUsuario(UsuarioModel usuarioModel){
        return usuarioRepository.findById(usuarioModel.getCdUsuario());
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel getUserByEmail(String email){
        return usuarioRepository.findByTxEmail(email);
    }

    public void completarInfoUsuario(UsuarioModel usuarioModel, PasswordEncoder passwordEncoder, Integer cdTipoUsuario) {
        usuarioModel.setTxSenha(passwordEncoder.encode(usuarioModel.getTxSenha()));
        usuarioModel.setTxRole("USER");

        TipoUsuarioModel tipoUsuarioModel = tipoUsuarioService.buscarTipoUsuario(cdTipoUsuario);
        usuarioModel.setTipoUsuarioModel(tipoUsuarioModel);
    }
}
