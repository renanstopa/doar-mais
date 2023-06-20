package com.api.doarmais.controllers;

import com.api.doarmais.exceptions.*;
import com.api.doarmais.models.AutenticacaoEmailModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import com.api.doarmais.services.AutenticacaoEmailService;
import com.api.doarmais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacaoemail")
public class AutenticacaoEmailController {

    @Autowired
    private AutenticacaoEmailService autenticacaoEmailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PatchMapping("/validar/{token}")
    public ResponseEntity<UsuarioModel> trocarSenha(@PathVariable("token") String token){
        if(!autenticacaoEmailService.verificarPedidoPorToken(token))
            throw new TokenDoesNotExists("URL inválida");

        AutenticacaoEmailModel autenticacaoEmailModel = autenticacaoEmailService.buscarPorToken(token);
        if(autenticacaoEmailModel.getCdSituacao().equals(SituacaoModel.TOKEN_UTILIZADO))
            throw new LinkAlreadyUsed("Esse link já foi utilizado para autenticar o email");

        autenticacaoEmailModel.setCdSituacao(SituacaoModel.TOKEN_UTILIZADO);
        autenticacaoEmailService.gravar(autenticacaoEmailModel);
        UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorEmail(autenticacaoEmailModel.getTxEmailUsuario());

        usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_SEM_APROVACAO_DO_ADM));
        return new ResponseEntity<UsuarioModel>(usuarioService.gravar(usuarioModel), HttpStatus.OK);
    }

}
