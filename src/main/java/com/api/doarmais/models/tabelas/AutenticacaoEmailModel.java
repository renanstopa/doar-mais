package com.api.doarmais.models.tabelas;

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
@Table(name = "tab_autenticacao_email")
public class AutenticacaoEmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_autenticacao_email")
    private Integer cdAutenticacaoEmail;

    @Column(name = "tx_email_usuario")
    private String txEmailUsuario;

    @Column(name = "cd_situacao")
    private Integer cdSituacao;

    @Column(name = "tx_token")
    private String txToken;

}
