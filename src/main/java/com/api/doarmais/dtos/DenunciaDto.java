package com.api.doarmais.dtos;

import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DenunciaDto {
    @NotNull
    private UsuarioModel usuarioModel;

    @NotNull
    private SituacaoModel situacaoModel;

    @NotBlank
    @Size(max = 400)
    private String descDenuncia;
}
