package com.api.doarmais.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_tipo_anuncio")
public class TipoAnuncioModel {
    @Id
    @Column(name = "cd_tipo_anuncio")
    private Integer cdTipoAnuncio;

    @Column(name = "tx_tipo_anuncio")
    private String txTipoAnuncio;
}
