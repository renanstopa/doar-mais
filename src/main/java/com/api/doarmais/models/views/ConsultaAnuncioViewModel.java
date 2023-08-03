package com.api.doarmais.models.views;

import com.api.doarmais.dtos.response.ItemAnuncioResponseDto;
import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.modelmapper.ModelMapper;
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
  private LocalDateTime dataInicioDisponibilidade;

  @Column(name = "data_fim_disponibilidade")
  private LocalDateTime dataFimDisponibilidade;

  @Column(name = "endereco_completo")
  private String enderecoCompleto;

  @Column(name = "nome")
  private String nome;

  @Column(name = "telefone")
  private String telefone;

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
