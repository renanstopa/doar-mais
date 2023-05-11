package com.api.doarmais.controllers;

import com.api.doarmais.dtos.UsuarioDto;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criarUsuario")
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return new ResponseEntity<UsuarioModel>(usuarioService.criarUsuario(usuarioModel), HttpStatus.CREATED);
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

}
