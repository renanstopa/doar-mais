package com.api.doarmais.models.views;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_consultar_troca_endereco")
public class ConsultaGerenciarTrocaEnderecoViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "cep")
  private String cep;

  @Column(name = "uf")
  private String uf;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "logradouro")
  private String logradouro;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "complemento")
  private String complemento;
}
