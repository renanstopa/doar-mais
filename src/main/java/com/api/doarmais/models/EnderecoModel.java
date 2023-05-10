package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_endereco")
    private Integer cdEndereco;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private UsuarioModel usuarioModel;

    @Column(name = "tx_cep")
    private String txCep;

    @Column(name = "tx_estado")
    private String txEstado;

    @Column(name = "tx_cidade")
    private String txCidade;

    @Column(name = "tx_logradouro")
    private String txLogradouro;

    @Column(name = "cd_numero")
    private Integer cdNumero;

    @Column(name = "tx_complemento")
    private String txComplemento;

}
