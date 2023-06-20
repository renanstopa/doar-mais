package com.api.doarmais.services;

import com.api.doarmais.models.*;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    public UsuarioModel gravar(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public Optional<UsuarioModel> buscarUsuario(UsuarioModel usuarioModel){
        return usuarioRepository.findById(usuarioModel.getCdUsuario());
    }

    public boolean verificarUsuarioPorEmail(String email){
        return usuarioRepository.existsByTxEmail(email);
    }

    public boolean verificarUsuarioPorDocumento(String documento){
        return usuarioRepository.existsByTxDocumento(documento);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByTxEmail(email);
    }

    public void completarInfoUsuario(UsuarioModel usuarioModel, PasswordEncoder passwordEncoder, Integer cdTipoUsuario) {
        usuarioModel.setTxSenha(passwordEncoder.encode(usuarioModel.getTxSenha()));
        usuarioModel.setTxRole("USER");

        TipoUsuarioModel tipoUsuarioModel = tipoUsuarioService.buscarTipoUsuario(cdTipoUsuario);
        usuarioModel.setTipoUsuarioModel(tipoUsuarioModel);

        usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_SEM_EMAIL_VERIFICADO));
    }

}
