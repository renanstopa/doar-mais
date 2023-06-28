package com.api.doarmais.controllers;

import com.api.doarmais.dtos.ResetDto;
import com.api.doarmais.dtos.TrocarSenhaDto;
import com.api.doarmais.exceptions.*;
import com.api.doarmais.models.tabelas.ResetSenhaModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.ResetSenhaService;
import com.api.doarmais.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resetsenha")
public class ResetSenhaController {

    @Autowired
    private ResetSenhaService resetSenhaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/enviaremail")
    public ResponseEntity<ResetSenhaModel> enviarEmail(@RequestBody @Valid ResetDto resetDto){

        if(!usuarioService.verificarUsuarioPorEmail(resetDto.getTxEmail())) {
            throw new UserNotFound("Email inválido");
        }

        if(resetSenhaService.verificarPedidoPorEmail(resetDto.getTxEmail())) {
            ResetSenhaModel resetSenhaModel = resetSenhaService.buscarPedido(resetDto.getTxEmail());
            if (resetSenhaService.expirou(resetSenhaModel)) {
                resetSenhaModel.setCdSituacao(SituacaoModel.TOKEN_EXPERIRADO);
                resetSenhaService.gravar(resetSenhaModel);
            } else {
                throw new ResetAlreadyExists("Um pedido de troca ainda está ativo");
            }
        }

        var pedidoGerado = resetSenhaService.gerarPedido(resetDto.getTxEmail());
        resetSenhaService.gravar(pedidoGerado);
        resetSenhaService.enviarEmail(pedidoGerado);

        return new ResponseEntity<ResetSenhaModel>(resetSenhaService.buscarPedidoCriado(pedidoGerado).get(), HttpStatus.CREATED);
    }

    @PatchMapping("/trocarsenha/{token}")
    public ResponseEntity<UsuarioModel> trocarSenha(@PathVariable("token") String token,
                                                    @RequestBody @Valid TrocarSenhaDto trocarSenhaDto){
        ResetSenhaModel resetSenhaModel = resetSenhaService.buscarPedidoPorToken(token);
        if(!resetSenhaService.verificarPedidoPorToken(token))
            throw new TokenDoesNotExists("URL inválida");

        if (resetSenhaService.expirou(resetSenhaModel))
            throw new ResetExpired("Seu pedido de alteração de senha já expirou");

        if(!resetSenhaModel.getCdSituacao().equals(SituacaoModel.TOKEN_UTILIZADO))
            throw new LinkAlreadyUsed("Esse link já foi utilizado para alterar a senha");

        if(!trocarSenhaDto.getSenha().equals(trocarSenhaDto.getConfirmaSenha()))
            throw new PasswordNotEqual("As senhas devem ser iguais");

        resetSenhaModel.setCdSituacao(SituacaoModel.TOKEN_UTILIZADO);
        resetSenhaService.gravar(resetSenhaModel);
        UsuarioModel usuarioModel = usuarioService.buscarUsuarioPorEmail(resetSenhaModel.getTxEmailUsuario());

        usuarioModel.setTxSenha(passwordEncoder.encode(trocarSenhaDto.getSenha()));
        return new ResponseEntity<UsuarioModel>(usuarioService.gravar(usuarioModel), HttpStatus.OK);
    }

}
