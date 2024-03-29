package com.api.doarmais.models.tabelas;

import com.api.doarmais.dtos.request.EditarAnuncioRequestDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anuncio")
public class AnuncioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_tipo_anuncio")
  private TipoAnuncioModel tipoAnuncioModel;

  @ManyToOne
  @JoinColumn(name = "id_usuario_criador")
  private UsuarioModel usuarioModel;

  @ManyToOne
  @JoinColumn(name = "id_situacao")
  private SituacaoModel situacaoModel;

  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "data_inicio_disponibilidade")
  private LocalDateTime dataInicioDisponibilidade;

  @Column(name = "data_fim_disponibilidade")
  private LocalDateTime dataFimDisponibilidade;

  @Column(name = "cep")
  private String cep;

  @Column(name = "uf")
  private String uf;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "logradouro")
  private String logradouro;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "complemento")
  private String complemento;

  @Column(name = "ponto_referencia")
  private String pontoReferencia;

  @Column(name = "quantidade_proposta")
  private Integer quantidadeProposta;

  public boolean verficarTrocaInfoPrincipal(EditarAnuncioRequestDto anuncioEditado) {
    if (!this.cep.equals(anuncioEditado.getCep())) return true;

    if (!this.getNumero().equals(anuncioEditado.getNumero())) return true;

    if (!this.getDataInicioDisponibilidade().equals(anuncioEditado.getDataInicioDisponibilidade()))
      return true;

    return !this.getDataFimDisponibilidade().equals(anuncioEditado.getDataFimDisponibilidade());
  }
}
