package com.api.doarmais.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_usuario")
    private TipoUsuarioModel tipoUsuarioModel;

    @ManyToOne
    @JoinColumn(name = "cd_situacao")
    private SituacaoModel situacaoModel;

    @Column(name = "tx_usuario")
    private String txUsuario;

    @Column(name = "tx_email")
    private String txEmail;

    @Column(name = "tx_senha")
    private String txSenha;

    @Column(name = "tx_telefone")
    private String txTelefone;

    @Column(name = "tx_documento")
    private String txDocumento;

    @Column(name = "tx_role")
    private String txRole;

    @Column(name = "img_comprovante_residencia")
    private String imgComprovanteResidencia;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(txRole));
    }

    @Override
    public String getPassword() {
        return txSenha;
    }

    @Override
    public String getUsername() {
        return txEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
