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
    @Column(name = "cd_usuario")
    private Integer cdUsuario;

    @Column(name = "tx_usuario")
    private String txUsuario;

    @Column(name = "tx_telefone")
    private String txTelefone;

    @Column(name = "tx_documento")
    private String txDocumento;

    @Column(name = "tx_cep")
    private String txCep;

    @Column(name = "tx_uf")
    private String txUf;

    @Column(name = "tx_cidade")
    private String txCidade;

    @Column(name = "tx_bairro")
    private String txBairro;

    @Column(name = "cd_numero")
    private Integer txNumero;

    @Column(name = "tx_complemento")
    private String txComplemento;

}
