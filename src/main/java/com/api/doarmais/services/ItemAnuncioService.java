package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.repositories.ItemAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemAnuncioService {

    @Autowired private ItemAnuncioRepository itemAnuncioRepository;

    public void gravar(ItemAnuncioModel itemAnuncioModel) {
        itemAnuncioRepository.save(itemAnuncioModel);
    }
}
