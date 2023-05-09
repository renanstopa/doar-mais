package com.api.doarmais.dtos;

import com.api.doarmais.models.AnuncioModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropostaDto {
    @NotNull
    private UsuarioModel usuarioModel;

    @NotNull
    private AnuncioModel anuncioModel;

    @NotNull
    private UsuarioModel usuarioAceitoModel;

    @NotNull
    private SituacaoModel situacaoModel;
}
