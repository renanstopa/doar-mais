package com.api.doarmais.controllers;

import com.api.doarmais.clients.BrasilApiClient;
import com.api.doarmais.dtos.CepDto;
import com.api.doarmais.dtos.RequestDto;
import com.api.doarmais.dtos.ResponseDto;
import com.api.doarmais.dtos.CriarUsuarioDto;
import com.api.doarmais.exceptions.CepNotFound;
import com.api.doarmais.models.EnderecoModel;
import com.api.doarmais.models.UsuarioModel;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private BrasilApiClient brasilApiClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrarUsuario")
    public ResponseEntity<UsuarioModel> registrarUsuario(@RequestBody @Valid CriarUsuarioDto criarUsuarioDto){
        CepDto retorno = brasilApiClient.buscarCep(criarUsuarioDto.getTxCep());
        if(retorno.getCep() == null){
            throw new CepNotFound(criarUsuarioDto.getTxCep() + " é um cep inválido");
        }

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(criarUsuarioDto, usuarioModel);
        usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 1);
        usuarioModel = usuarioService.criarUsuario(usuarioModel);

        var enderecoModel = new EnderecoModel();
        enderecoService.armazenarEndereco(usuarioModel, enderecoModel, retorno, criarUsuarioDto);
        enderecoService.criarEndereco(enderecoModel);

        return new ResponseEntity<UsuarioModel>(usuarioService.buscarUsuario(usuarioModel).get(), HttpStatus.CREATED);
    }

    @PostMapping("/registrarOng")
    public ResponseEntity<UsuarioModel> registrarOng(@RequestBody @Valid CriarUsuarioDto criarUsuarioDto){
        CepDto retorno = brasilApiClient.buscarCep(criarUsuarioDto.getTxCep());
        if(retorno.getCep() == null){
            throw new CepNotFound(criarUsuarioDto.getTxCep() + " é um cep inválido");
        }

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(criarUsuarioDto, usuarioModel);
        usuarioService.completarInfoUsuario(usuarioModel, passwordEncoder, 2);
        usuarioModel = usuarioService.criarUsuario(usuarioModel);

        var enderecoModel = new EnderecoModel();
        enderecoService.armazenarEndereco(usuarioModel, enderecoModel, retorno, criarUsuarioDto);
        enderecoService.criarEndereco(enderecoModel);

        return new ResponseEntity<UsuarioModel>(usuarioService.buscarUsuario(usuarioModel).get(), HttpStatus.CREATED);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<ResponseDto> authenticate(
            @RequestBody RequestDto request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
