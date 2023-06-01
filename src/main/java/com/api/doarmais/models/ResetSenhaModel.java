package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_reset_senha")
public class ResetSenhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_reset_senha")
    private Integer cdResetSenha;

    @Column(name = "tx_email_usuario")
    private String txEmailUsuario;

    @Column(name = "cd_situacao")
    private Integer cdSituacao;

    @Column(name = "tx_token")
    private String txToken;

    @Column(name = "dt_validade_token")
    private LocalDateTime dtValidadeToken;

}
