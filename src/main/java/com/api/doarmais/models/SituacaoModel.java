package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_situacao")
public class SituacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_situacao")
    private Integer cdSituacao;

    @Column(name = "tx_situacao")
    private String txSituacao;

}
