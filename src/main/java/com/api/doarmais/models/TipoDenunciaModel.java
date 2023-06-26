package com.api.doarmais.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_tipo_denuncia")
public class TipoDenunciaModel {

    @Id
    @Column(name = "cd_tipo_denuncia")
    private Integer cdTipoDenuncia;

    @Column(name = "tx_tipo_denuncia")
    private String txTipoDenuncia;

}
