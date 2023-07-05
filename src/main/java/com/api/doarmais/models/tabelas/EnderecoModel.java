package com.api.doarmais.models.tabelas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
public class EnderecoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private UsuarioModel usuarioModel;

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

  @Column(name = "ativo")
  private Integer ativo;
}
