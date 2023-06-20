package com.api.doarmais.services;

import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.TipoUsuarioModel;
import com.api.doarmais.repositories.SituacaoRepository;
import com.api.doarmais.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituacaoService {

    @Autowired
    private SituacaoRepository situacaoRepository;

    public boolean verificarExistencia() {
        return situacaoRepository.existsById(1);
    }

    public void saveAllSituacoes(List<SituacaoModel> situacaoModels) {
        situacaoRepository.saveAll(situacaoModels);
    }
}
