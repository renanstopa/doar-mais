package com.api.doarmais.dtos;

import com.api.doarmais.models.SituacaoModel;
import com.api.doarmais.models.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DenunciaDto {

    @NotNull(message = "Denúncia não pode ser nula!")
    private UsuarioModel usuarioModel;

    @NotBlank(message = "Descrição da Denúncia deve ser preenchida!")
    @Size(max = 400, message = "Descrição da Denúncia deve conter no máximo 400 caracteres!")
    private String descDenuncia;

}
