package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tab_denuncia")
public class DenunciaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_denuncia")
    private Integer cdDenuncia;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private UsuarioModel usuarioModel;

    @ManyToOne
    @JoinColumn(name = "cd_situacao")
    private SituacaoModel situacaoModel;

    @Column(name = "desc_denuncia")
    private String descDenuncia;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "dt_denuncia")
    private LocalDateTime dtDenuncia;

}
