package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_usuario")
    private TipoUsuarioModel tipoUsuarioModel;

    @ManyToOne
    @JoinColumn(name = "cd_situacao")
    private SituacaoModel situacaoModel;

    @Column(name = "tx_usuario")
    private String txUsuario;

    @Column(name = "tx_telefone")
    private String txTelefone;

    @Column(name = "tx_cpf")
    private String txCpf;

    @Column(name = "txCnpj")
    private String txCnpj;

    @Column(name = "img_comprovante_residencia")
    private String imgComprovanteResidencia;

}
