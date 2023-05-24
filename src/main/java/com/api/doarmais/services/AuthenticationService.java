package com.api.doarmais.services;

import com.api.doarmais.dtos.RequestDto;
import com.api.doarmais.dtos.ResponseDto;
import com.api.doarmais.dtos.UsuarioDto;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseDto register(UsuarioDto request) {
        var user = UsuarioModel.builder()
                .txUsuario(request.getTxUsuario())
                .txTelefone(request.getTxTelefone())
                .txDocumento(request.getTxDocumento())
                .txEmail(request.getTxEmail())
                .txSenha(passwordEncoder.encode(request.getTxSenha()))
                .tipoUsuarioModel(request.getTipoUsuarioModel())
                .txRole("USER")
                .build();
        usuarioRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseDto authenticate(RequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getTxEmail(),
                        request.getTxSenha()
                )
        );
        var user = usuarioRepository.findByTxEmail(request.getTxEmail());
        var jwtToken = jwtService.generateToken(user);
        return ResponseDto.builder()
                .token(jwtToken)
                .build();
    }

}
