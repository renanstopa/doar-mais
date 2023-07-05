package com.api.doarmais.dtos.request;

import com.api.doarmais.models.tabelas.TipoDenunciaModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DenunciaRequestDto {

  @NotNull(message = "Tipo denuncia não pode ser nula!")
  private TipoDenunciaModel tipoDenunciaModel;

  private UsuarioModel usuarioModel;

  @NotBlank(message = "Descrição denuncia deve ser preenchida!")
  @NotNull(message = "Descrição denuncia não pode ser nula!")
  @Size(max = 400, message = "Descrição denuncia deve ter no máximo 400 caracteres")
  private String descricao;
}
