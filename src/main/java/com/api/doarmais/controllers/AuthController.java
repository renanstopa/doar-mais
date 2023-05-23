package com.api.doarmais.controllers;

import com.api.doarmais.dtos.RequestDto;
import com.api.doarmais.dtos.ResponseDto;
import com.api.doarmais.dtos.UsuarioDto;
import com.api.doarmais.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(
            @RequestBody UsuarioDto request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate(
            @RequestBody RequestDto request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
