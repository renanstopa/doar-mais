package com.api.doarmais.services;

import com.api.doarmais.models.TipoDenunciaModel;
import com.api.doarmais.repositories.TipoDenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDenunciaService {

    @Autowired
    private TipoDenunciaRepository tipoDenunciaRepository;

    public boolean verificarExistencia() {
        return tipoDenunciaRepository.existsById(1);
    }

    public void saveAllTipoDenuncias(List<TipoDenunciaModel> tipoDenunciaModels) {
        tipoDenunciaRepository.saveAll(tipoDenunciaModels);
    }
}
