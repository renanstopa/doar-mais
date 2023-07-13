package com.api.doarmais.configs;

import com.api.doarmais.models.tabelas.*;
import com.api.doarmais.services.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataExecutor implements CommandLineRunner {

  @Autowired private TipoUsuarioService tipoUsuarioService;

  @Autowired private UsuarioService usuarioService;

  @Autowired private SituacaoService situacaoService;

  @Autowired private TipoDenunciaService tipoDenunciaService;

  @Autowired private TipoAnuncioService tipoAnuncioService;

  @Autowired private CategoriaItemService categoriaItemService;

  @Override
  public void run(String... args) throws Exception {
    if (!tipoUsuarioService.verificarExistencia()) {
      List<TipoUsuarioModel> tipoUsuarioModels =
          Arrays.asList(
              TipoUsuarioModel.builder().id(1).descricao("Pessoa").build(),
              TipoUsuarioModel.builder().id(2).descricao("ONG").build(),
              TipoUsuarioModel.builder().id(3).descricao("Administrador").build());
      tipoUsuarioService.saveAllTipoUsuarios(tipoUsuarioModels);
    }

    if (!tipoDenunciaService.verificarExistencia()) {
      List<TipoDenunciaModel> tipoDenunciaModels =
          Arrays.asList(
              TipoDenunciaModel.builder().id(1).descricao("Melhoria").build(),
              TipoDenunciaModel.builder().id(2).descricao("Bug").build(),
              TipoDenunciaModel.builder().id(3).descricao("Denunciar usuário").build());
      tipoDenunciaService.saveAllTipoDenuncias(tipoDenunciaModels);
    }

    if (!situacaoService.verificarExistencia()) {
      List<SituacaoModel> situacaoModels =
          Arrays.asList(
              SituacaoModel.builder().id(1).descricao("Token não utilizado").build(),
              SituacaoModel.builder().id(2).descricao("Token expirado").build(),
              SituacaoModel.builder().id(3).descricao("Token utilizado").build(),
              SituacaoModel.builder().id(11).descricao("Conta sem email verificado").build(),
              SituacaoModel.builder()
                  .id(12)
                  .descricao("Conta sem aprovação do admnistrador")
                  .build(),
              SituacaoModel.builder().id(13).descricao("Conta apta para uso").build(),
              SituacaoModel.builder().id(14).descricao("Conta suspensa").build(),
              SituacaoModel.builder().id(15).descricao("Conta encerrada").build(),
              SituacaoModel.builder().id(21).descricao("Anúncio criado").build(),
              SituacaoModel.builder().id(22).descricao("Anúncio sem itens").build(),
              SituacaoModel.builder().id(23).descricao("Anúncio cancelado").build(),
              SituacaoModel.builder().id(24).descricao("Anúncio finalizado").build(),
              SituacaoModel.builder().id(31).descricao("Proposta confirmada").build(),
              SituacaoModel.builder().id(32).descricao("Proposta cancelada").build(),
              SituacaoModel.builder().id(33).descricao("Encontro realizado").build(),
              SituacaoModel.builder().id(34).descricao("Encontro não realizado").build(),
              SituacaoModel.builder().id(41).descricao("Denúncia criada").build(),
              SituacaoModel.builder().id(42).descricao("Denúncia gerenciada").build());
      situacaoService.saveAllSituacoes(situacaoModels);

      if (!tipoAnuncioService.verificarExistencia()) {
        List<TipoAnuncioModel> tipoAnuncioModels =
            Arrays.asList(
                TipoAnuncioModel.builder().id(1).descricao("Doação").build(),
                TipoAnuncioModel.builder().id(2).descricao("Pedido").build(),
                TipoAnuncioModel.builder().id(3).descricao("Doação Rápida").build());
        tipoAnuncioService.saveAllTipoAnuncios(tipoAnuncioModels);
      }

      if (!categoriaItemService.verificarExistencia()) {
        List<CategoriaItemModel> categoriaItemModels =
            Arrays.asList(
                CategoriaItemModel.builder().id(1).descricao("Alimento").build(),
                CategoriaItemModel.builder().id(2).descricao("Eletrônico").build(),
                CategoriaItemModel.builder().id(3).descricao("Eletrodoméstico").build(),
                CategoriaItemModel.builder().id(4).descricao("Roupa").build(),
                CategoriaItemModel.builder().id(5).descricao("Móvel").build(),
                CategoriaItemModel.builder().id(6).descricao("Entretenimento").build(),
                CategoriaItemModel.builder().id(7).descricao("Pet").build(),
                CategoriaItemModel.builder().id(8).descricao("Outro").build());
        categoriaItemService.saveAllCategoriaItens(categoriaItemModels);
      }
    }
  }
}
