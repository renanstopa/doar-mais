package com.api.doarmais.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vw_perfil_usuario")
public class PerfilUsuarioViewModel {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "id_endereco")
  private Integer idEndereco;

  @Column(name = "nome")
  private String nome;

  @Column(name = "telefone")
  private String telefone;

  @Column(name = "documento")
  private String documento;

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
