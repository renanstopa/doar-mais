package com.api.doarmais.models.views;

import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import com.api.doarmais.models.tabelas.ItemAnuncioPropostaModel;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@Table(name = "vw_consulta_prosposta")
public class ConsultaPropostaViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "id_usuario_proposta")
  private Integer idUsuarioProposta;

  @Column(name = "nome_usuario_proposta")
  private String nomeUsuarioProposta;

  @Column(name = "telefone_usuario_proposta")
  private String telefoneUsuarioProposta;

  @Column(name = "id_usuario_anuncio")
  private Integer idUsuarioAnuncio;

  @Column(name = "nome_usuario_anuncio")
  private String nomeUsuarioAnuncio;

  @Column(name = "telefone_usuario_anuncio")
  private String telefoneUsuarioAnuncio;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "cep")
  private String cep;

  @Column(name = "uf")
  private String uf;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "logradouro")
  private String logradouro;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "complemento")
  private String complemento;

  @Column(name = "ponto_referencia")
  private String pontoReferencia;

  @Column(name = "data_agendada")
  private String dataAgendada;

  @Transient private Integer podeConfirmarEncontro;

  @Transient private Integer podeCancelarProposta;

  @Transient private List<ItemAnuncioResponseDto> itemList = new ArrayList<>();

  public void armazenarItens(List<ItemAnuncioPropostaModel> itemAnuncioPropostaModel) {
    for (ItemAnuncioPropostaModel itens : itemAnuncioPropostaModel) {
      ItemAnuncioResponseDto response = new ItemAnuncioResponseDto();
      ItemAnuncioModel itemAnuncioModel = itens.getItemAnuncioModel();
      BeanUtils.copyProperties(itemAnuncioModel, response);
      response.setQuantidade(itens.getQuantidadeSolicitada());
      itemList.add(response);
    }
  }
}
