package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.TipoAnuncioModel;
import com.api.doarmais.repositories.TipoAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAnuncioService {

    @Autowired private TipoAnuncioRepository tipoAnuncioRepository;

    public boolean verificarExistencia() {
        return tipoAnuncioRepository.existsById(1);
    }

    public void saveAllTipoAnuncios(List<TipoAnuncioModel> tipoAnuncioModels) {
        tipoAnuncioRepository.saveAll(tipoAnuncioModels);
    }
}
