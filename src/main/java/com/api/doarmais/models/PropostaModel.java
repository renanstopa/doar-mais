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
