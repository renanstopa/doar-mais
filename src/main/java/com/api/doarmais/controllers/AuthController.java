package com.api.doarmais.controllers;

import com.api.doarmais.dtos.*;
import com.api.doarmais.exceptions.UserAlreadyExists;
import com.api.doarmais.models.AutenticacaoEmailModel;
import com.api.doarmais.models.EnderecoModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.services.AutenticacaoEmailService;
import com.api.doarmais.services.AuthenticationService;
import com.api.doarmais.services.EnderecoService;
import com.api.doarmais.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AutenticacaoEmailService autenticacaoEmailService;

    @PostMapping("/registrarusuario")
    public ResponseEntity<UsuarioModel> registrarUsuario(@RequestBody @Valid CriarUsuarioDto criarUsuarioDto){

        if (usuarioService.verificarUsuarioPorEmail(criarUsuarioDto.getTxEmail()))
            throw new UserAlreadyExists("Email já cadastrado");

        if (usuarioService.verificarUsuarioPorDocumento(criarUsuarioDto.getTxDocumento()))
            throw new UserAlreadyExists("CPF já cadastrado");

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(criarUsuarioDto, usuarioModel);
        usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 1);
        usuarioModel = usuarioService.gravar(usuarioModel);

        var enderecoModel = new EnderecoModel();
        enderecoService.armazenarEndereco(usuarioModel, enderecoModel, criarUsuarioDto);
        enderecoService.criarEndereco(enderecoModel);

        AutenticacaoEmailModel autenticacaoGerada = autenticacaoEmailService.gerarAutenticacaoEmail(usuarioModel.getTxEmail());
        autenticacaoEmailService.gravar(autenticacaoGerada);
        autenticacaoEmailService.enviarEmail(autenticacaoGerada);

        return new ResponseEntity<UsuarioModel>(usuarioService.buscarUsuario(usuarioModel).get(), HttpStatus.CREATED);
    }

    @PostMapping("/registrarong")
    public ResponseEntity<UsuarioModel> registrarOng(@RequestBody @Valid CriarUsuarioDto criarUsuarioDto){

        if (usuarioService.verificarUsuarioPorEmail(criarUsuarioDto.getTxEmail()))
            throw new UserAlreadyExists("Email já cadastrado");

        if (usuarioService.verificarUsuarioPorDocumento(criarUsuarioDto.getTxDocumento()))
            throw new UserAlreadyExists("CNPJ já cadastrado");

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(criarUsuarioDto, usuarioModel);
        usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 2);
        usuarioModel = usuarioService.gravar(usuarioModel);

        var enderecoModel = new EnderecoModel();
        enderecoService.armazenarEndereco(usuarioModel, enderecoModel, criarUsuarioDto);
        enderecoService.criarEndereco(enderecoModel);

        AutenticacaoEmailModel autenticacaoGerada = autenticacaoEmailService.gerarAutenticacaoEmail(usuarioModel.getTxEmail());
        autenticacaoEmailService.gravar(autenticacaoGerada);
        autenticacaoEmailService.enviarEmail(autenticacaoGerada);

        return new ResponseEntity<UsuarioModel>(usuarioService.buscarUsuario(usuarioModel).get(), HttpStatus.CREATED);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<ResponseDto> authenticate(@RequestBody RequestDto request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
