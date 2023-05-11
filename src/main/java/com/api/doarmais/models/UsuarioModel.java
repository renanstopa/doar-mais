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

    @Column(name = "fl_tipo_documento")
    private char flTipoDocumento;

    @Column(name = "tx_documento")
    private String txDocumento;

    @Column(name = "img_comprovante_residencia")
    private String imgComprovanteResidencia;

}
