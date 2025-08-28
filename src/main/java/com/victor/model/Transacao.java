package com.victor.model;

import java.util.Date;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victor.enums.TipoTransacao;
import com.victor.enums.converters.TipoTransacaoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "transacoes")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idTransacao;

  @NotNull
  @Length(max = 255)
  @Column(length = 255, nullable = false)
  private String descricao;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double valor;

  @NotNull
  @Column(nullable = false)
  private Date dataTransacao;

  @NotNull
  @Column(nullable = false)
  @Convert(converter = TipoTransacaoConverter.class)
  private TipoTransacao tipo; // 'C' para crédito, 'D' para débito

  @NotNull
  @Positive
  @Column(nullable = false)
  private Integer parcelas = 1;

  @NotNull
  @Valid
  @ManyToOne(optional = false)
  @JoinColumn(name = "idUsuario", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario usuario;

  @Valid
  @ManyToOne(optional = true)
  @JoinColumn(name = "idConta")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Conta conta;

  @Valid
  @ManyToOne(optional = true)
  @JoinColumn(name = "idCategoria")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Categoria categoria;

  public Long getIdTransacao() {
    return idTransacao;
  }

  public void setIdTransacao(Long idTransacao) {
    this.idTransacao = idTransacao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(@NotNull @Length(max = 255) String descricao) {
    this.descricao = descricao;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(@NotNull @PositiveOrZero Double valor) {
    this.valor = valor;
  }

  public Date getDataTransacao() {
    return dataTransacao;
  }

  public void setDataTransacao(@NotNull Date dataTransacao) {
    this.dataTransacao = dataTransacao;
  }

  public TipoTransacao getTipo() {
    return tipo;
  }

  public void setTipo(@NotNull TipoTransacao tipo) {
    this.tipo = tipo;
  }

  public Integer getParcelas() {
    return parcelas;
  }

  public void setParcelas(@NotNull @Positive Integer parcelas) {
    this.parcelas = parcelas;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(@NotNull @Valid Usuario usuario) {
    this.usuario = usuario;
  }

  public Conta getConta() {
    return conta;
  }

  public void setConta(@Valid Conta conta) {
    this.conta = conta;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(@Valid Categoria categoria) {
    this.categoria = categoria;
  }
}
