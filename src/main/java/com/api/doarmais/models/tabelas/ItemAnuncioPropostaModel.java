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
@Table(name = "tab_item_anuncio_proposta")
public class ItemAnuncioPropostaModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_proposta")
    private PropostaModel cdProposta;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_item")
    private ItemAnuncioModel cdItem;

    @Column(name = "qtd_solicitada_item")
    private Integer qtdSolicitadaItem;

}
