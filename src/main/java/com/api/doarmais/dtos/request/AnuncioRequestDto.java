package com.api.doarmais.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AnuncioRequestDto {

    @NotBlank(message = "Título deve ser preenchido!")
    @NotNull(message = "Título não pode ser nulo!")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres!")
    private String titulo;

    @Valid
    List<ItemAnuncioRequestDto> listaItens;

    @NotNull(message = "Data início não pode ser nula!")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInicioDisponibilidade;

    @NotNull(message = "Data fim não pode ser nula!")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFimDisponibilidade;

    @NotBlank(message = "CEP deve ser preenchido!")
    @NotNull(message = "CEP não pode ser nulo!")
    @Size(max = 8, message = "CEP deve ter no máximo 8 caracteres!")
    private String cep;

    @NotBlank(message = "UF deve ser preenchido!")
    @NotNull(message = "UF não pode ser nulo!")
    @Size(max = 2, message = "UF deve ter no máximo 2 caracteres!")
    private String uf;

    @NotBlank(message = "Cidade deve ser preenchido!")
    @NotNull(message = "Cidade não pode ser nulo!")
    @Size(max = 150, message = "Cidade deve ter no máximo 150 caracteres!")
    private String cidade;

    @NotNull(message = "Número não pode ser nulo!")
    private Integer numero;

    @Size(max = 100, message = "Compelemento deve conter no máximo 100 caracteres!")
    private String complemento;

    @Size(max = 100, message = "Ponto de referência deve conter no máximo 100 caracteres!")
    private String pontoReferencia;

}
