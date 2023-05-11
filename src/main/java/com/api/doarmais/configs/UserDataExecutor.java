package com.api.doarmais.configs;

import com.api.doarmais.models.TipoUsuarioModel;
import com.api.doarmais.services.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDataExecutor implements CommandLineRunner {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Override
    public void run(String... args) throws Exception {
        tipoUsuarioService.deleteAllTipoUsuarios();
        List<TipoUsuarioModel> tipoUsuarioModels = Arrays.asList(
                TipoUsuarioModel.builder().cdTipoUsuario(1).txTipoUsuario("Pessoa").build(),
                TipoUsuarioModel.builder().cdTipoUsuario(2).txTipoUsuario("ONG").build(),
                TipoUsuarioModel.builder().cdTipoUsuario(3).txTipoUsuario("Administrador").build()
        );
        tipoUsuarioService.saveAllTipoUsuarios(tipoUsuarioModels);
    }

}
