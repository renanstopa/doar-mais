package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tab_item_anuncio")
public class ItemAnuncioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Column(name = "quantidade_item")
    private Integer quantidadeItem;

}
