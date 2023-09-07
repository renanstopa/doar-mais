package com.api.doarmais.models.views;

import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "vw_consulta_prosposta_confirmada")
public class ConsultaPropostaConfirmadaViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

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

  @Transient
  private List<ItemAnuncioResponseDto> itemList = new ArrayList<>();

  public void armazenarItens(List<ItemAnuncioModel> itemAnuncioModelList){
    for (ItemAnuncioModel itens : itemAnuncioModelList) {
      ItemAnuncioResponseDto response = new ItemAnuncioResponseDto();
      BeanUtils.copyProperties(itens, response);
      itemList.add(response);
    }
  }
}
