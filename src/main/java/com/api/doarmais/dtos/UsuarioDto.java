package com.api.doarmais.dtos;

import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.TipoUsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDto {
    @NotNull
    private TipoUsuarioModel tipoUsuarioModel;

    @NotNull
    private SituacaoModel situacaoModel;

    @NotBlank
    @Size(max = 200)
    private String txUsuario;

    @NotBlank
    @Size(min = 11, max = 11)
    private String txTelefone;

    @Size(max = 11)
    private String txCpf;

    @Size(max = 14)
    private String txCnpj;

    @NotBlank
    private String imgComprovanteResidencia;
}
