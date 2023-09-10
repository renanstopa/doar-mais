package com.api.doarmais.models.views;

import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@Table(name = "vw_consulta_anuncio")
public class ConsultaAnuncioViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_usuario_criador")
  private Integer idUsuarioCriador;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "data_inicio_disponibilidade")
  private String dataInicioDisponibilidade;

  @Column(name = "data_fim_disponibilidade")
  private String dataFimDisponibilidade;

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

  @Column(name = "nome")
  private String nome;

  @Column(name = "telefone")
  private String telefone;

  @Transient private List<ItemAnuncioResponseDto> itemList = new ArrayList<>();

  public void armazenarItens(List<ItemAnuncioModel> itemAnuncioModelList) {
    for (ItemAnuncioModel itens : itemAnuncioModelList) {
      ItemAnuncioResponseDto response = new ItemAnuncioResponseDto();
      BeanUtils.copyProperties(itens, response);
      itemList.add(response);
    }
  }
}
