package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.AnuncioModel;
import com.api.doarmais.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired private AnuncioRepository anuncioRepository;

    public AnuncioModel gravar(AnuncioModel anuncioModel) {
        return anuncioRepository.save(anuncioModel);
    }
}
