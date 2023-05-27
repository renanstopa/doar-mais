package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_anuncio")
public class AnuncioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "tx_cep")
    private String txCep;

    @Column(name = "tx_cidade")
    private String txCidade;

    @Column(name = "tx_uf")
    private String txUf;

}
