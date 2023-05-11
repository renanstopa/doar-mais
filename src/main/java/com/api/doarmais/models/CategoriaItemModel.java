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
@Table(name = "tab_categoria_item")
public class CategoriaItemModel {

    @Id
    @Column(name = "cd_categoria_item")
    private Integer cdCategoriaItem;

    @Column(name = "tx_categoria_item")
    private String txCategoriaItem;

}
