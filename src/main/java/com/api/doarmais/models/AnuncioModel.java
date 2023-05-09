package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tab_anuncio")
public class AnuncioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_anuncio")
    private Integer cdAnuncio;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_anuncio")
    private TipoAnuncioModel tipoAnuncioModel;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "dt_criacao_anuncio")
    private LocalDateTime dtCriacaoAnuncio;

    @Column(name = "desc_anuncio")
    private String descAnuncio;
}
