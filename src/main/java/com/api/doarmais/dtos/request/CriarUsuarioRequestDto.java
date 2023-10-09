package com.api.doarmais.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CriarUsuarioRequestDto {

  @NotBlank(message = "Nome do Usuário deve ser preenchido!")
  @NotNull(message = "Nome do Usuário não pode ser nulo!")
  @Size(max = 200, message = "Nome do Usuário deve conter no máximo 200 caracteres!")
  private String nome;

  @NotBlank(message = "Telefone deve ser preenchido!")
  @NotNull(message = "Telefone não pode ser nulo!")
  @Size(min = 10, max = 11, message = "Telefone deve conter entre 10 e 11 números!")
  private String telefone;

  @Size(max = 14, message = "Documento deve conter no máximo 14 números!")
  private String documento;

  @NotBlank(message = "Email deve ser preenchido!")
  @NotNull(message = "Email não pode ser nulo!")
  @Email(message = "Email inválido!")
  @Size(max = 200, message = "Email deve ter no máximo 200 caracteres!")
  private String email;

  @NotBlank(message = "Senha deve ser preenchido!")
  @NotNull(message = "Senha não pode ser nulo!")
  @Size(max = 20, message = "Senha deve ter no máximo 20 caracteres!")
  private String senha;

  @NotBlank(message = "CEP deve ser preenchido!")
  @NotNull(message = "CEP não pode ser nulo!")
  @Size(max = 8, message = "CEP deve ter no máximo 8 caracteres!")
  private String cep;

  @NotBlank(message = "Cidade deve ser preenchido!")
  @NotNull(message = "Cidade não pode ser nulo!")
  @Size(max = 150, message = "Cidade deve ter no máximo 150 caracteres!")
  private String cidade;

  @NotBlank(message = "UF deve ser preenchido!")
  @NotNull(message = "UF não pode ser nulo!")
  @Size(max = 2, message = "UF deve ter no máximo 2 caracteres!")
  private String uf;

  @NotBlank(message = "Bairro deve ser preenchido!")
  @NotNull(message = "Bairro não pode ser nulo!")
  @Size(max = 150, message = "Bairro deve ter no máximo 150 caracteres!")
  private String bairro;

  @NotBlank(message = "Logradouro deve ser preenchido!")
  @NotNull(message = "Logradouro não pode ser nulo!")
  @Size(max = 200, message = "Logradouro deve ter no máximo 200 caracteres!")
  private String logradouro;

  @NotNull(message = "Número não pode ser nulo!")
  private Integer numero;

  @Size(max = 100, message = "Compelemento deve conter no máximo 100 caracteres!")
  private String complemento;

  @NotNull(message = "Comprovante deve ser informado!")
  private MultipartFile comprovante;
}
