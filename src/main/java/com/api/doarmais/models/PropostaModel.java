package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tab_proposta")
public class PropostaModel {
    @Id
    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private UsuarioModel usuarioModel;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_anuncio")
    private AnuncioModel anuncioModel;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_usuario_aceito")
    private UsuarioModel usuarioAceitoModel;

    @Id
    @ManyToOne
    @JoinColumn(name = "cd_situacao")
    private SituacaoModel situacaoModel;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "dt_validade")
    private LocalDateTime dtValidade;
}
