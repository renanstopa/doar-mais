package com.api.doarmais.models.views;

import com.api.doarmais.models.tabelas.ItemAnuncioModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "vw_busca_anuncio")
public class BuscaAnuncioViewModel {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_tipo_anuncio")
    private Integer id_tipo_anuncio;

    @Column(name = "id_tipo_usuario")
    private Integer id_tipo_usuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_inicio_disponibilidade")
    private LocalDateTime dataInicioDisponibilidade;

    @Column(name = "data_fim_disponibilidade")
    private LocalDateTime dataFimDisponibilidade;

    @Column(name = "cidade")
    private String cidade;

}
