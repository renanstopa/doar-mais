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
@Table(name = "tab_item_anuncio")
public class ItemAnuncioModel {

    @Id
    @GeneratedValue
    @Column(name = "cd_item")
    private Integer cdItem;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_anuncio")
    private AnuncioModel anuncioModel;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_categoria_item")
    private CategoriaItemModel categoriaItemModel;

    @Column(name = "qtd_item")
    private Integer qtdItem;

}
