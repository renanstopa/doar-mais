package com.api.doarmais.controllers;

import com.api.doarmais.dtos.request.DenunciaRequestDto;
import com.api.doarmais.dtos.response.DenunciaResponseDto;
import com.api.doarmais.models.tabelas.DenunciaModel;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.services.DenunciaService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/denuncia")
public class DenunciaController {

  @Autowired private DenunciaService denunciaService;
  @Autowired private ModelMapper modelMapper;

  @PostMapping("/criardenuncia")
  public ResponseEntity<DenunciaResponseDto> criarDenuncia(
      @Valid @RequestBody DenunciaRequestDto denunciaRequestDto) {
    var denunciaModel = new DenunciaModel();
    BeanUtils.copyProperties(denunciaRequestDto, denunciaModel);
    denunciaModel.setSituacaoModel(new SituacaoModel(SituacaoModel.DENUNCIA_CRIADA));
    denunciaModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

    return new ResponseEntity<DenunciaResponseDto>(
        modelMapper.map(denunciaService.gravar(denunciaModel), DenunciaResponseDto.class),
        HttpStatus.CREATED);
  }
}
