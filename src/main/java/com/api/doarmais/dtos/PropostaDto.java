package com.api.doarmais.dtos;

import com.api.doarmais.models.AnuncioModel;
import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropostaDto {
    //TALVEZ EXCLUA ESSE DTO
    @NotNull(message = "Usuário não pode ser nulo!")
    private UsuarioModel usuarioModel;

    @NotNull(message = "Anúncio não pode ser nulo!")
    private AnuncioModel anuncioModel;

    @NotNull(message = "Usuário Aceito não pode ser nulo!")
    private UsuarioModel usuarioAceitoModel;

    @NotNull(message = "Situação não pode ser nulo!")
    private SituacaoModel situacaoModel;
}
