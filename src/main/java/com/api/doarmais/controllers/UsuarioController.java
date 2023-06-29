package com.api.doarmais.controllers;

import com.api.doarmais.dtos.AtualizarDadosDto;
import com.api.doarmais.dtos.EnderecoDto;
import com.api.doarmais.dtos.TrocarSenhaDto;
import com.api.doarmais.exceptions.AddressAlreadyExists;
import com.api.doarmais.exceptions.PasswordNotEqual;
import com.api.doarmais.models.tabelas.EnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.models.views.PerfilUsuarioViewModel;
import com.api.doarmais.services.EnderecoService;
import com.api.doarmais.services.PerfilUsuarioViewService;
import com.api.doarmais.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilUsuarioViewService perfilUsuarioViewService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/perfil")
    public ResponseEntity<PerfilUsuarioViewModel> perfilUsuario(){
        UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<PerfilUsuarioViewModel>(perfilUsuarioViewService.consultarPerfil(usuarioModel.getCdUsuario()).get(), HttpStatus.FOUND);
    }

    @PatchMapping("/atualizardados")
    public ResponseEntity<UsuarioModel> atualizarDados(@Valid @RequestBody AtualizarDadosDto atualizarDadosDto){
        UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BeanUtils.copyProperties(atualizarDadosDto, usuarioModel);
        return new ResponseEntity<UsuarioModel>(usuarioService.gravar(usuarioModel), HttpStatus.OK);
    }

    @PostMapping("/criarendereco")
    public ResponseEntity<EnderecoModel> criarEndereco(@Valid @RequestBody EnderecoDto enderecoDto){
        UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(enderecoService.verificarEnderecoExistente(enderecoDto.getTxCep(), enderecoDto.getCdNumero(), usuarioModel.getCdUsuario())){
            throw new AddressAlreadyExists("Endereço já cadastrado");
        }

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setCkAtivo(2);
        enderecoModel.setUsuarioModel(usuarioModel);

        return new ResponseEntity<EnderecoModel>(enderecoService.gravar(enderecoModel), HttpStatus.CREATED);
    }

    @PatchMapping("/trocarendereco/{endereco}")
    public ResponseEntity<EnderecoModel> trocarEndereco(@PathVariable("endereco") Integer endereco){
        UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EnderecoModel enderecoAtual = enderecoService.buscarEnderecoAtivo(usuarioModel.getCdUsuario());
        EnderecoModel novoEnderecoAtivo = enderecoService.buscarNonoEndereco(endereco).get();

        enderecoAtual.setCkAtivo(2);
        enderecoService.gravar(enderecoAtual);

        novoEnderecoAtivo.setCkAtivo(1);
        enderecoService.gravar(novoEnderecoAtivo);

        return new ResponseEntity<EnderecoModel>(novoEnderecoAtivo, HttpStatus.OK);
    }

    @PatchMapping("/trocarsenha")
    public ResponseEntity<UsuarioModel> trocarSenha(@Valid @RequestBody TrocarSenhaDto trocarSenhaDto){
        if(!trocarSenhaDto.getSenha().equals(trocarSenhaDto.getConfirmaSenha()))
            throw new PasswordNotEqual("As senhas devem ser iguais");

        UsuarioModel usuarioModel = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        usuarioModel.setTxSenha(passwordEncoder.encode(trocarSenhaDto.getSenha()));

        return new ResponseEntity<UsuarioModel>(usuarioService.gravar(usuarioModel), HttpStatus.OK);
    }


}
