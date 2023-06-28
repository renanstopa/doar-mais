package com.api.doarmais.models.tabelas;

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
@Table(name = "tab_denuncia")
public class DenunciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_denuncia")
    private Integer cdDenuncia;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_denuncia")
    private TipoDenunciaModel tipoDenunciaModel;

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
