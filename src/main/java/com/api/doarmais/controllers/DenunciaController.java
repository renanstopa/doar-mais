package com.api.doarmais.controllers;

import com.api.doarmais.dtos.DenunciaDto;
import com.api.doarmais.models.DenunciaModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.services.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/denuncia")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping("/criardenuncia")
    public ResponseEntity<DenunciaModel> criarDenuncia(@Valid @RequestBody DenunciaDto denunciaDto){
        var denunciaModel = new DenunciaModel();
        BeanUtils.copyProperties(denunciaDto, denunciaModel);
        denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_CRIADA));
        denunciaModel.setDtDenuncia(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        return new ResponseEntity<DenunciaModel>(denunciaService.gravar(denunciaModel), HttpStatus.CREATED);
    }

}
