package com.api.doarmais.controllers;

import com.api.doarmais.dtos.request.AnuncioRequestDto;
import com.api.doarmais.dtos.request.ItemAnuncioRequestDto;
import com.api.doarmais.dtos.response.AnuncioResponseDto;
import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.TipoAnuncioModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.services.AnuncioService;
import com.api.doarmais.services.ItemAnuncioService;
import com.api.doarmais.services.TipoAnuncioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired private AnuncioService anuncioService;

    @Autowired private ItemAnuncioService itemAnuncioService;

    @Autowired private TipoAnuncioService tipoAnuncioService;

    @Autowired private ModelMapper modelMapper;

//    @GetMapping()
//    public ResponseEntity<?> listarAnuncios(){
//        return null;
//    }
//
//    @GetMapping()
//    public ResponseEntity<?> visualizarAnuncio(){
//        return null;
//    }

    @PostMapping("/criardoacao")
    public ResponseEntity<AnuncioResponseDto> criarDoacao(@Valid @RequestBody AnuncioRequestDto anuncioRequestDto){
        var anuncioModel = new AnuncioModel();
        BeanUtils.copyProperties(anuncioRequestDto, anuncioModel);
        anuncioModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        anuncioModel.setTipoAnuncioModel(new TipoAnuncioModel(2));

        var usuarioCriador = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        anuncioModel.setUsuarioModel(usuarioCriador);

        anuncioModel = anuncioService.gravar(anuncioModel);

        for (ItemAnuncioRequestDto itemDto : anuncioRequestDto.getListaItens()) {
            var itemAnuncioModel = new ItemAnuncioModel();
            itemAnuncioModel.setAnuncioModel(anuncioModel);
            BeanUtils.copyProperties(itemDto, itemAnuncioModel);
            itemAnuncioService.gravar(itemAnuncioModel);
        }

        List<ItemAnuncioModel> listaItens = itemAnuncioService.buscaPorAnuncio(anuncioModel.getId());
        List<ItemAnuncioResponseDto> listaItensResponse = new ArrayList<ItemAnuncioResponseDto>();
        for (ItemAnuncioModel item : listaItens) {
            listaItensResponse.add(modelMapper.map(item, ItemAnuncioResponseDto.class));
        }
        AnuncioResponseDto response = modelMapper.map(anuncioModel, AnuncioResponseDto.class);
        response.setItens(listaItensResponse);

        return new ResponseEntity<AnuncioResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping("/criarpedido")
    public ResponseEntity<AnuncioModel> criarPedido(@Valid @RequestBody AnuncioRequestDto anuncioRequestDto){
        var anuncioModel = new AnuncioModel();
        BeanUtils.copyProperties(anuncioRequestDto, anuncioModel);
        anuncioModel.setDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        anuncioModel.setTipoAnuncioModel(new TipoAnuncioModel(2));

        var usuarioCriador = (UsuarioModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        anuncioModel.setUsuarioModel(usuarioCriador);

        anuncioModel = anuncioService.gravar(anuncioModel);

        for (ItemAnuncioRequestDto itemDto : anuncioRequestDto.getListaItens()) {
            var itemAnuncioModel = new ItemAnuncioModel();
            itemAnuncioModel.setAnuncioModel(anuncioModel);
            BeanUtils.copyProperties(itemDto, itemAnuncioModel);
            itemAnuncioService.gravar(itemAnuncioModel);
        }

        return new ResponseEntity<AnuncioModel>(anuncioModel, HttpStatus.CREATED);
    }

}
