package com.api.doarmais.configs;

import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TipoDenunciaModel;
import com.api.doarmais.models.tabelas.TipoUsuarioModel;
import com.api.doarmais.services.SituacaoService;
import com.api.doarmais.services.TipoDenunciaService;
import com.api.doarmais.services.TipoUsuarioService;
import com.api.doarmais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDataExecutor implements CommandLineRunner {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SituacaoService situacaoService;

    @Autowired
    private TipoDenunciaService tipoDenunciaService;

    @Override
    public void run(String... args) throws Exception {
        if(!tipoUsuarioService.verificarExistencia()){
            List<TipoUsuarioModel> tipoUsuarioModels = Arrays.asList(
                    TipoUsuarioModel.builder().cdTipoUsuario(1).txTipoUsuario("Pessoa").build(),
                    TipoUsuarioModel.builder().cdTipoUsuario(2).txTipoUsuario("ONG").build(),
                    TipoUsuarioModel.builder().cdTipoUsuario(3).txTipoUsuario("Administrador").build()
            );
            tipoUsuarioService.saveAllTipoUsuarios(tipoUsuarioModels);
        }

        if(!tipoDenunciaService.verificarExistencia()){
            List<TipoDenunciaModel> tipoDenunciaModels = Arrays.asList(
                    TipoDenunciaModel.builder().cdTipoDenuncia(1).txTipoDenuncia("Melhoria").build(),
                    TipoDenunciaModel.builder().cdTipoDenuncia(2).txTipoDenuncia("Bug").build(),
                    TipoDenunciaModel.builder().cdTipoDenuncia(3).txTipoDenuncia("Denunciar usuário").build()
            );
            tipoDenunciaService.saveAllTipoDenuncias(tipoDenunciaModels);
        }

        if(!situacaoService.verificarExistencia()){
            List<SituacaoModel> situacaoModels = Arrays.asList(
                    SituacaoModel.builder().cdSituacao(1).txSituacao("Token não utilizado").build(),
                    SituacaoModel.builder().cdSituacao(2).txSituacao("Token expirado").build(),
                    SituacaoModel.builder().cdSituacao(3).txSituacao("Token utilizado").build(),

                    SituacaoModel.builder().cdSituacao(11).txSituacao("Conta sem email verificado").build(),
                    SituacaoModel.builder().cdSituacao(12).txSituacao("Conta sem aprovação do admnistrador").build(),
                    SituacaoModel.builder().cdSituacao(13).txSituacao("Conta apta para uso").build(),
                    SituacaoModel.builder().cdSituacao(14).txSituacao("Conta suspensa").build(),
                    SituacaoModel.builder().cdSituacao(15).txSituacao("Conta encerrada").build(),

                    SituacaoModel.builder().cdSituacao(21).txSituacao("Anúncio criado").build(),
                    SituacaoModel.builder().cdSituacao(22).txSituacao("Anúncio sem itens").build(),
                    SituacaoModel.builder().cdSituacao(23).txSituacao("Anúncio cancelado").build(),
                    SituacaoModel.builder().cdSituacao(24).txSituacao("Anúncio finalizado").build(),

                    SituacaoModel.builder().cdSituacao(31).txSituacao("Proposta confirmada").build(),
                    SituacaoModel.builder().cdSituacao(32).txSituacao("Proposta cancelada").build(),
                    SituacaoModel.builder().cdSituacao(33).txSituacao("Encontro realizado").build(),
                    SituacaoModel.builder().cdSituacao(34).txSituacao("Encontro não realizado").build(),

                    SituacaoModel.builder().cdSituacao(41).txSituacao("Denúncia criada").build(),
                    SituacaoModel.builder().cdSituacao(42).txSituacao("Denúncia gerenciada").build()
            );
            situacaoService.saveAllSituacoes(situacaoModels);
        }

    }

}
