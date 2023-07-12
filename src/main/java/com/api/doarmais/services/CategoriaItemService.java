package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.CategoriaItemModel;
import com.api.doarmais.repositories.CategoriaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaItemService {

    @Autowired private CategoriaItemRepository categoriaItemRepository;

    public boolean verificarExistencia() {
        return categoriaItemRepository.existsById(1);
    }

    public void saveAllCategoriaItens(List<CategoriaItemModel> categoriaItemModels) {
        categoriaItemRepository.saveAll(categoriaItemModels);
    }
}
