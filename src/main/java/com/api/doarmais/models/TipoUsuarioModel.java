package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_tipo_usuario")
public class TipoUsuarioModel {
    @Id
    @Column(name = "cd_tipo_usuario")
    private Integer cdTipoUsuario;

    @Column(name = "tx_tipo_usuario")
    private String txTipoUsuario;
}
