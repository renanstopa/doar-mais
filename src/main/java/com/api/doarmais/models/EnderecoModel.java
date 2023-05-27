package com.api.doarmais.models;

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
@Table(name = "tab_endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_endereco")
    private Integer cdEndereco;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private UsuarioModel usuarioModel;

    @Column(name = "tx_cep")
    private String txCep;

    @Column(name = "txUf")
    private String txUf;

    @Column(name = "tx_bairro")
    private String txBairro;

    @Column(name = "tx_cidade")
    private String txCidade;

    @Column(name = "tx_logradouro")
    private String txLogradouro;

    @Column(name = "cd_numero")
    private Integer cdNumero;

    @Column(name = "tx_complemento")
    private String txComplemento;

}
